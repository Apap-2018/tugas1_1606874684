package com.apap.tugas1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apap.tugas1.model.InstansiModel;
import com.apap.tugas1.model.JabatanModel;
import com.apap.tugas1.model.PegawaiModel;
import com.apap.tugas1.model.ProvinsiModel;

public interface PegawaiDB extends JpaRepository<PegawaiModel, Long>{
	PegawaiModel findById(long id);
	PegawaiModel findByNip(String nip);
	List<PegawaiModel> findByInstansiOrderByTanggalLahirAsc(InstansiModel instansiModel);
	List<PegawaiModel> findByTahunMasukAndInstansi(String tahun_masuk, InstansiModel instansi);
	List<PegawaiModel> findByInstansiAndJabatan(InstansiModel instansi, JabatanModel jabatan);
	List<PegawaiModel> findByInstansi(InstansiModel instansi); 
	}
