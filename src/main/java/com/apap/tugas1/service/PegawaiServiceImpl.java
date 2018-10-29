package com.apap.tugas1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;
import com.apap.tugas1.repository.PegawaiDB;

@Service
@Transactional
public class PegawaiServiceImpl implements PegawaiService{
	@Autowired
	private PegawaiDB pegawaiDb;
	
	@Autowired
	private InstansiService instansiService; 
	
	@Override
	public PegawaiModel getDetailPegawaiByNip (String nip) {
		return pegawaiDb.findByNip(nip);
	}
	
	public List<PegawaiModel> findInstansiOrder(InstansiModel instansiModel) {
		return pegawaiDb.findByInstansiOrderByTanggalLahirAsc(instansiModel);
	}
	
	@Override
	public List<PegawaiModel> findPegawaiByTahunMasukAndInstansi(String tahunMasuk, InstansiModel instansi) {
		return pegawaiDb.findByTahunMasukAndInstansi(tahunMasuk, instansi);
	}

	@Override
	public List<PegawaiModel> findPegawaiByInstansiAndJabatan(InstansiModel instansi, JabatanModel jabatan) {
		return pegawaiDb.findByInstansiAndJabatan(instansi, jabatan);
	}

	@Override
	public List<PegawaiModel> findPegawaiByInstansi(InstansiModel instansi) {
		return pegawaiDb.findByInstansi(instansi);
	}

	@Override
	public void addPegawai(PegawaiModel pegawai) {
		
		
	}

	@Override
	public List<PegawaiModel> findAll() {
		return pegawaiDb.findAll();
	}
	
}

