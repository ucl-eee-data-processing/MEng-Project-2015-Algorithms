package meng_project.algorithms;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import org.apache.commons.math3.stat.descriptive.moment.Mean;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataProcessor {

	List<double[]> obsListX = new ArrayList<double[]>();
	List<String> obsListY = new ArrayList<String>();
	List<String> timeList = new ArrayList<String>();

	List<String> listX = new ArrayList<String>();
	List<String> listY = new ArrayList<String>();

	List<Double> averageList = new ArrayList<Double>();
	List<String> averageDayList = new ArrayList<String>();
	
	List<Double> obsListTime = new ArrayList<Double>();
	List<String> timeListTime = new ArrayList<String>();

	double[] obsArrayX = new double[2];

	double time;
	double temperature;
	double energy;

	int dayOfWeek;
	int hourOfDay;
	int minuteOfDay;

	String keyDate;
	String keyTime;

	String Time;
	double mean;

	TimeProcessor tp = new TimeProcessor();
	Mean mn = new Mean();

	@SuppressWarnings("unused")
	public void setTime(int hour,int minute) {
		this.hourOfDay = hour;
		this.minuteOfDay = minute;

	}

	@SuppressWarnings("unused")
	private void addToTemp(double obsTemp) {
		this.temperature = obsTemp;
	}
	
	@SuppressWarnings("rawtypes")
	public void readData(int day, boolean today, boolean average, boolean timeOfDay) { // put input if
																// it's for
																// specified
																// day, break

		try {

			JSONObject json = (JSONObject) Main.obj;

			for (Iterator iterator = json.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				String key1 = null;

				JSONObject dateObj = (JSONObject) json.get(key);

				this.keyDate = key;

				for (Iterator iterator2 = dateObj.keySet().iterator(); iterator2.hasNext();) {
					key1 = (String) iterator2.next();
					JSONObject energyObj = (JSONObject) dateObj.get(key1);
					String imEnergy1 = (String) energyObj.get("Total Imported Energy");
					String ttlElec = (String) energyObj.get("Total Imported Electricity");

					this.Time = this.keyDate + "-" + key1;

					if (average == true && tp.isDayOfWeek(this.Time, day)) {
						mn.increment((Double) Double.parseDouble(imEnergy1));
					}if(timeOfDay == true && tp.isTimeOfDay(this.Time)){
						this.timeListTime.add(this.Time);
						this.obsListTime.add((Double) Double.parseDouble(imEnergy1));												
					}
					
					this.timeList.add(this.Time);
					this.obsListY.add(imEnergy1);
					System.out.println(this.Time + "  energy --->  " + imEnergy1 + " electricty ---->" + ttlElec);

				}
				if (average == true && tp.isDayOfWeek(this.Time, day)) {
					averageList.add(mn.getResult());
					averageDayList.add(this.Time);
					mn.clear();
				}
				
				if (today == true) {
					break;
				}

				System.out.println(key + ";askdjaskjdlaskjdklasjdkasjdlkasjdkljdlkasjdlkasjdlkasjdlkasjd");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
