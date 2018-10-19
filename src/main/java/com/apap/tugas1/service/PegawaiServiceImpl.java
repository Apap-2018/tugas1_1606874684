package com.apap.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.repository.PegawaiDB;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{
	@Autowired
	private PegawaiDB pegawaiDb;
	
//	@Autowired
//	private InstansiService instansiService; 
	
	@Override
	public PegawaiModel getDetailPegawaiByNip (String nip) {
		return pegawaiDb.findByNip(nip);
	}
	
	public List<PegawaiModel> findInstansiOrder(InstansiModel instansiModel) {
		return pegawaiDb.findByInstansiOrderByTanggalLahirAsc(instansiModel);
	}
	
/**	@Override
	public void addPegawai(PegawaiModel pegawai){
		// ID instansi (4 digit) -> instansi. search ID. 
		String idInstansi = instansiService.getIdByName(pegawai.getInstansi());
		
		// Tanggal lahir (6 digit) dd-MM-yy. 06-12-1997. 061297.
		String ttl = pegawai.getTanggal_lahir() 
		
		// Tahun mulai (4 digit)
		String tahunMulai = pegawai.getTahun_masuk();
				
		// 2 digit tgl lahir dan tahun mulai yang sama.
		int count = //query db yg tgl lahir sm tahun masuk sama.
		// if count = 0
			String digitMasuk = "01";
		// count > 0
			int digit = count + 1;
			if(digitMasuk.length() < 2)
				String digitMasuk = "0" + digit;
			else
				String digitMasuk = digit + "";
			
		String NIP = new StringBuilder()
				.append(idInstansi)
				.append(ttl)
				.append(tahunMulai)
				.append(digitMasuk)
				.toString();
		
		pegawai.setNip(NIP);

		pegawaiDb.save(pegawai);
	}
**/	
}

