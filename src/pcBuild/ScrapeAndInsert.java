package pcBuild;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ScrapeAndInsert {
	public static void main(String args[]) throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM","password");
		Statement st = con.createStatement();
		
		String create = "create table pcbproducts( prid varchar(6) primary key, pname varchar(500), pimg varchar(500), pdesc varchar(500), type varchar(20) not null, price number(10,2))";
		System.out.println(create);
		st.execute(create);
		System.out.println("Processors");
		Product Processor = new Product("https://www.pcstudio.in/pc-build/","processor","PRO");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("MotherBoard");
		Product MotherBoard = new Product("https://www.pcstudio.in/pc-build/?step=2","motherboard","MTB");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("RAM");
		Product RAM = new Product("https://www.pcstudio.in/pc-build/?step=3","ram","RAM");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("Storage");
		Product Storage = new Product("https://www.pcstudio.in/pc-build/?step=4","storage","STO");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("Cabinet");
		Product Cabinet = new Product("https://www.pcstudio.in/pc-build/?step=6","cabinet","CAB");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("Cooler");
		Product Cooler = new Product("https://www.pcstudio.in/pc-build/?step=7","cooler","COL");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("GPU");
		Product GPU = new Product("https://www.pcstudio.in/pc-build/?step=8","gpu","GPU");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("PowerSupply");
		Product PowerSupply = new Product("https://www.pcstudio.in/pc-build/?step=9","PowerSupply","PWS");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		System.out.println("Display");
		Product Display = new Product("https://www.pcstudio.in/pc-build/?step=10","display","DIS");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		
		con.close();
	}
}


class Product{
	ArrayList<String> proName = new ArrayList<String>();
	ArrayList<String> proUrl = new ArrayList<String>();
	ArrayList<String> proPrice = new ArrayList<String>();
	ArrayList<String> proImg = new ArrayList<String>();
	Product(String url, String table, String ID) throws Exception{
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","SYSTEM","password");
			Statement st = con.createStatement();
			Document D = Jsoup.connect (url).timeout(10000).post();
			Elements e = D.select("div.woopb-product-title");
			for(Element ele: e) {
				this.proName.add(ele.text());
				String link  = ele.select("a").attr("href");
				this.proUrl.add(link);
			}
			System.out.println(this.proName.size());
			
			Elements el = D.select("div.woopb-product-price");
			System.out.println(el.size());
				for(Element ele: el) {
					String temp = ele.text();
					temp = temp.replace('₹',' ');
					temp = temp.replace(",","");
					this.proPrice.add(temp.split(" ")[1]);
				}
				
				
			Elements img = D.select("div.woopb-product-left").select("div.woocommerce-product-gallery__image");
			for(Element ele: img) {
				String imglink = ele.select("a").attr("href");
				this.proImg.add(imglink);
				System.out.println(imglink);
			}
			
			for(int i = 0;i<this.proPrice.size();i++ ) {
				System.out.println(this.proName.get(i) + "\t\t\t\t" + this.proPrice.get(i));
				System.out.println(this.proUrl.get(i) + '\n');
			}
			
			System.out.println("\n\n\n");
			for(int i = 0;i<this.proName.size();i++) {
				String Exec = String.format("insert into pcbproducts values ('%s%03d','%s','%s','%s','%s', ", ID, i+1, this.proName.get(i), this.proImg.get(i), this.proUrl.get(i), table);
				Exec = Exec + this.proPrice.get(i) + ")";
				st.executeUpdate(Exec);
				System.out.println("Inserted  " + (i+1));	
			}
			
			con.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(url);
			System.out.println("\n\n\n");
		}
	}
}
