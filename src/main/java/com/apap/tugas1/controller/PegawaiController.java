package com.apap.tugas1.controller;

import java.util.List;

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
import com.apap.tugas1.service.InstansiService;
import com.apap.tugas1.service.PegawaiService;

@Controller
public class PegawaiController {
	@Autowired
	private PegawaiService pegawaiService;
	
	@Autowired
	private InstansiService instansiService;
	
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
	
/**	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.GET)
	private String add(Model model) {
		PegawaiModel pegawai = new PegawaiModel();
		
		model.addAttribute("pegawai", pegawai);
		return "tambah-pegawai";
	}
	
	@RequestMapping(value = "/pegawai/tambah", method = RequestMethod.POST)
	private String addJabatan(@ModelAttribute PegawaiModel pegawai, Model model) {
		pegawaiService.addPegawai(pegawai);
		
		model.addAttribute("pegawai", pegawai);
		return "tambah";
	}**/
	
}
