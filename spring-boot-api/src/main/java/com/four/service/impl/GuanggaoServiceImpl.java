package com.four.service.impl;

import com.four.dao.GuanggaoDao;
import com.four.model.GuangGao;
import com.four.service.GuanggaoService;
import com.four.util.CommonCode;
import com.four.util.CommonUtil;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("guanggaoService")
public class GuanggaoServiceImpl implements GuanggaoService {
	@Autowired
	private GuanggaoDao guanggaoDao;



	@Override
	public JSONObject queryGuangGao(Integer offset, Integer limit) {

		Long count = guanggaoDao.getSum();

		List<GuangGao> list = guanggaoDao.seriesList(offset,limit);
		JSONObject json =new JSONObject();
		json.put("total", count);
		json.put("rows", list);
		return json;
	}


	@Override
	public void deleteGuangGao(String guangGao) {
		List list = CommonUtil.getidList(guangGao);
		for (int i = 0; i < list.size(); i++) {

			guanggaoDao.deleteGuangGao(list.get(i).toString());
		}
	}

	@Override
	public GuangGao queryGuangGaoById(GuangGao guangGao) {
		return guanggaoDao.queryGuangGaoById(guangGao);
	}

	@Override
	public void updateGuangGaoById(GuangGao guangGao) {
		guanggaoDao.updateGuangGaoById(guangGao);

	}

	@Override
	public void saveGuangGaoByModel(GuangGao guangGao) {
		guanggaoDao.saveGuangGaoByModel(guangGao);
	}

	@Override
	public List<GuangGao> queryGuangGaoQianTai() {
		List<GuangGao> list = guanggaoDao.queryGuangGaoQianTai( CommonCode.getGGZT());
		return list;
	}

	@Override
	public List<GuangGao> guangGaoManager() {
		return guanggaoDao.guangGaoManager();
	}



}
