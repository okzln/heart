package com.heartpet.action;

import java.util.List;

import com.heartpet.model.AnimalDTO;

public interface AnimalDAO {
	public int insert(AnimalDTO dto);
    public int update(AnimalDTO dto);
    public void delete(int id);
    public List<AnimalDTO> list();
    public AnimalDTO content(int id);
    public List<AnimalDTO> search();
}
