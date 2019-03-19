package com.cybertek;

public class WorkWithExcel {

	public static void main(String[] args) {
		
		//String path="/Users/mina/⁨Desktop⁩⁩/⁨testData.xlsx";
		String path = "/Users/mina/Desktop/testData.xlsx";
		Xls_Reader data = new Xls_Reader(path);
		
		int rcount = data.getRowCount("data");
		System.out.println(rcount);
		
		String cdata = data.getCellData("data", "name", 2);
		System.out.println(cdata);
		
		int ccount= data.getColumnCount("data");
		System.out.println(ccount);
		
		data.setCellData("data", "name", 8, "Bakeri");	
		
	}

//@DataProvider
public String[] getdata() {
	
	String[] data = new String[3];
	data[0]="Mike";
	data[1]="Smith";
	data[2]="Muna";
	
	return data;
}
}