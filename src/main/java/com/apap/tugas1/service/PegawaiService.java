package com.apap.tugas1.service;

import java.util.List;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.PegawaiModel;

public interface PegawaiService {
	PegawaiModel getDetailPegawaiByNip(String nip);
	List <PegawaiModel> findInstansiOrder(InstansiModel instansiModel);
	//void addPegawai(PegawaiModel pegawai);
}
