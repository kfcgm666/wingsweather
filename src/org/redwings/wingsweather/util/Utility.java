package org.redwings.wingsweather.util;

import org.redwings.wingsweather.db.WingsWeatherDB;
import org.redwings.wingsweather.model.City;
import org.redwings.wingsweather.model.County;
import org.redwings.wingsweather.model.Province;

import android.text.TextUtils;

public class Utility {

	/**
	 * �����ʹ�����������ص�ʡ������
	 */
	public synchronized static boolean handleProvincesResponse(WingsWeatherDB wingsWeatherDB, String response) {
		//�����ֳ�����synchronized�ؼ��֣�����Ϊʲôֻ���ڽ����ʹ���ʡ�����ݵ�ʱ����ã��������м����ؼ���ʱ��ȴ���ã�Ϊë��������
		if (!TextUtils.isEmpty(response)) {
			String[] allProvinces = response.split(",");
			if (allProvinces != null && allProvinces.length > 0) {
				for (String p : allProvinces) {
					String[] array = p.split("\\|");
					Province province = new Province();
					province.setProvinceCode(array[0]);
					province.setProvinceName(array[1]);
					//���������������ݴ洢��Province��
					wingsWeatherDB.saveProvince(province);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * �����ʹ�����������ص��м�����
	 */
	public static boolean handleCitiesResponse(WingsWeatherDB wingsWeatherDB, String response, int provinceId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCities = response.split(",");
			if (allCities != null && allCities.length > 0) {
				for (String c : allCities) {
					String[] array = c.split("\\|");
					City city = new City();
					city.setCityCode(array[0]);
					city.setCityName(array[1]);
					city.setProvinceId(provinceId);
					//���������������ݴ洢��City��
					wingsWeatherDB.saveCity(city);
				}
				return true;
			}
		}
		return false;
	}
	
	/**
	 * �����ʹ�����������ص��ؼ�����
	 */
	public static boolean handleCountiesResponse(WingsWeatherDB wingsWeatherDB, String response, int cityId) {
		if (!TextUtils.isEmpty(response)) {
			String[] allCounties = response.split(",");
			if (allCounties != null && allCounties.length > 0) {
				for (String c : allCounties) {
					String[] array = c.split("\\|");
					County county = new County();
					county.setCountyCode(array[0]);
					county.setCountyName(array[1]);
					county.setCityId(cityId);
					//���������������ݴ洢��County��
					wingsWeatherDB.saveCounty(county);
				}
				return true;
			}
		}
		return false;
	}
	
}
