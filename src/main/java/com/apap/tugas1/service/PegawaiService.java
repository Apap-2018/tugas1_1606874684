package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;

public interface PegawaiService {
	PegawaiModel getDetailPegawaiByNip(String nip);
	List <PegawaiModel> findInstansiOrder(InstansiModel instansiModel);
	void addPegawai(PegawaiModel pegawai);
	List <PegawaiModel> findAll();
	List <PegawaiModel> findPegawaiByTahunMasukAndInstansi(String tahunMasuk, InstansiModel instansi);
	List <PegawaiModel> findPegawaiByInstansiAndJabatan(InstansiModel instansi, JabatanModel jabatan);
	List <PegawaiModel> findPegawaiByInstansi(InstansiModel instansi);
}
