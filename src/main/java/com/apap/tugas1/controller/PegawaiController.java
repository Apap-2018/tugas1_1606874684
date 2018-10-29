package com.apap.tugas1.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.JabatanService;
import com.apap.tugas1.service.PegawaiService;
import com.apap.tugas1.service.ProvinsiService;

@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	
	@Autowired
	private InstansiService instansiService;
	
	@Autowired
	private ProvinsiService provinsiService;
	
	@Autowired
	private JabatanService jabatanService;
	
	@RequestMapping(value = "/pegawai", method = RequestMethod.GET)
	private String viewPegawai(@RequestParam("nip") String nip, Model model) {
		PegawaiModel pegawai = pegawaiService.getDetailPegawaiByNip(nip);
		
		model.addAttribute("pegawai", pegawai);
		return "view-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/termuda-tertua", method = RequestMethod.GET)
	private String viewPegawaiTermudaTertua(@RequestParam("id") long id, Model model) {
		InstansiModel instansi = instansiService.getInstansiDetailById(id);
		List<PegawaiModel> pegawaiList = pegawaiService.findInstansiOrder(instansi);

		PegawaiModel termuda = pegawaiList.get(pegawaiList.size()-1);
		PegawaiModel tertua = pegawaiList.get(0);

		List<JabatanModel> jabatanTermuda = termuda.getJabatan();
		List<JabatanModel> jabatanTertua = tertua.getJabatan();
		
		model.addAttribute("instansi", instansi);
		model.addAttribute("termuda", termuda);
		model.addAttribute("tertua", tertua);
		model.addAttribute("jabatanTermuda", jabatanTermuda);
		model.addAttribute("jabatanTertua", jabatanTertua);
		return "view-pegawai-termudatertua";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String add(Model model) {
		PegawaiModel pegawai = new PegawaiModel();
		List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();
		List<InstansiModel> listInstansi = instansiService.getAllInstansi();
		List<JabatanModel> listJabatan = jabatanService.getAllJabatan();
		
		model.addAttribute("pegawai", pegawai);
		model.addAttribute("listProvinsi", listProvinsi);
		model.addAttribute("listInsitansi", listInstansi);
		model.addAttribute("listJabatan", listJabatan);
		return "tambah-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	private String addPegawai(@ModelAttribute PegawaiModel pegawai, Model model) {
		pegawaiService.addPegawai(pegawai);
		String digitMasuk;
		
		//set-set pegawai belum
		
		//generate nip
		// ID instansi (4 digit)  
		Long idInstansi = pegawai.getInstansi().getId();
		String idInsStr = Long.toString(idInstansi);
		
		// Tanggal lahir (6 digit) dd-MM-yy. 06-12-1997. 061297.
		DateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		String tanggalLahir = dateFormat.format(pegawai.getTanggalLahir());
		
		// Tahun mulai (4 digit)
		String tahunMasuk = pegawai.getTahunMasuk();
				
		// 2 digit tgl lahir dan tahun mulai yang sama.
		List<PegawaiModel> listPegawaiSama = pegawaiService.findPegawaiByTahunMasukAndInstansi(tahunMasuk, pegawai.getInstansi());
		int count = listPegawaiSama.size();
		if (count == 0) {
			digitMasuk = "01";
		}
		int digit = count + 1;
		digitMasuk = Integer.toString(digit);
		
		if(digitMasuk.length() < 2) {
			digitMasuk = "0" + digit;
		}
		
		String nip = idInsStr + tanggalLahir + tahunMasuk + digitMasuk;
		
		pegawai.setNip(nip);

		//pegawaiDb.save(pegawai);
		
	
		model.addAttribute("pegawai", pegawai);
		return "tambah";
	}
	
	@RequestMapping(value = "/pegawai/ubah", method = RequestMethod.POST)
	private String changePegawai(@ModelAttribute PegawaiModel pegawai, Model model) {
		return "ubah-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/cari", method = RequestMethod.GET)
    public String searchPegawai(Model model) {

        List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();

        List<InstansiModel> listInstansi = instansiService.getAllInstansi();
        List<JabatanModel> listJabatan = jabatanService.getAllJabatan();

        
        System.out.println("masuk method cari yang pertama");
        System.out.println(listProvinsi.get(0).getNama() + " list provinsi");
        System.out.println(listInstansi.get(0).getNama() + " list instansi");
        System.out.println(listInstansi.size());
        System.out.println(listJabatan.get(0).getNama() + " list Jabatan");
        
        model.addAttribute("listProvinsi", listProvinsi);
        model.addAttribute("listInstansi", listInstansi);
        model.addAttribute("listJabatan", listJabatan);

        return "cari-pegawai";
    }

	@RequestMapping(value = "/pegawai/cari", method = RequestMethod.GET, params= {"search"})
    public String searchPegawaiSubmit(@RequestParam(value="idProvinsi", required=false) Optional<Long> idProvinsi,
               @RequestParam(value="idInstansi", required=false) Optional<Long> idInstansi,
               @RequestParam(value="idJabatan", required=false) Optional<Long> idJabatan, Model model) {



        List<PegawaiModel> listPegawai = new ArrayList<PegawaiModel>();
        if (idProvinsi.isPresent()) {
            if (idInstansi.isPresent()) {
                if (idJabatan.isPresent()) {
                    InstansiModel instansi = instansiService.getInstansiDetailById(idInstansi.get());
                    JabatanModel jabatan = jabatanService.getJabatanDetailById(idJabatan.get());

                    listPegawai = pegawaiService.findPegawaiByInstansiAndJabatan(instansi, jabatan);

                }
                else {
                    InstansiModel instansi = instansiService.getInstansiDetailById(idInstansi.get());
                    listPegawai = pegawaiService.findPegawaiByInstansi(instansi);
                }

            }
            else {
                if (idJabatan.isPresent()) {
                	List<InstansiModel> listInstansi = provinsiService.findById(idProvinsi.get()).getProvinsiInstansi();
          
                    for (int i = 0; i < listInstansi.size(); i++) {
                        List<PegawaiModel> listPegawaiBaru = listInstansi.get(i).getInstansiPegawai();
                        for (int j = 0; j < listPegawaiBaru.size(); j++) {
                            for (int k = 0; k < listPegawaiBaru.get(j).getJabatanPegawaiList().size(); k++) {
                                if (listPegawaiBaru.get(j).getJabatanPegawaiList().get(k).getJabatan().getId() == idJabatan.get()) {
                                    listPegawai.add(listPegawaiBaru.get(j));
                                    break;
                                }
                            }

                        }
                    }


                }
                else {
                    List<InstansiModel> listInstansi = provinsiService.findById(idProvinsi.get()).getProvinsiInstansi();
                    for (int i = 0; i < listInstansi.size(); i++) {
                        List<PegawaiModel> listPegawaiBaru = listInstansi.get(i).getInstansiPegawai();
                        listPegawai.addAll(listPegawaiBaru);
                    }
                }
            }
        }
        else {
            if (idJabatan.isPresent()) {
            	
                JabatanModel jabatan = jabatanService.getJabatanDetailById(idJabatan.get());
                for (int i = 0; i< jabatan.getPegawai().size(); i++) {
                    listPegawai.add(jabatan.getJabatanPegawaiList().get(i).getPegawai());
                }

            }
            if (idInstansi.isPresent()) {
            	InstansiModel instansi = instansiService.getInstansiDetailById(idInstansi.get());
            	for (int i=0; i<instansi.getInstansiPegawai().size();i++) {
            		listPegawai.add(instansi.getInstansiPegawai().get(i));
            	}
            }
        }

        List<ProvinsiModel> listProvinsi = provinsiService.getAllProvinsi();

        List<InstansiModel> listInstansi = new ArrayList<InstansiModel>();
        listInstansi = listProvinsi.get(0).getProvinsiInstansi();
        List<JabatanModel> listJabatan = jabatanService.getAllJabatan();

        model.addAttribute("title", "Cari Pegawai");
        model.addAttribute("listProvinsi", listProvinsi);
        model.addAttribute("listInstansi", listInstansi);
        model.addAttribute("listJabatan", listJabatan);
        model.addAttribute("listPegawai", listPegawai);
        return "cari-pegawai";
    
    }
}
