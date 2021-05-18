<h1 align="center"><img align="center" src="https://user-images.githubusercontent.com/64122408/118642285-c8965580-b7f8-11eb-9838-b1780fbc7dbc.png" height=50px width=50px>&emsp;PC Build</h1>
<br/>

<div align="center">

[![GitHub license](https://img.shields.io/github/license/Naereen/StrapDown.js.svg)](https://github.com/codervignesh/PC-Build/blob/master/LICENSE)&emsp;&emsp;
[![Open Source](https://badges.frapsoft.com/os/v1/open-source.svg?v=103)](https://opensource.org/)&emsp;&emsp;

</div>
<br/>

<h1 align="center"><b>What's This? :brain:</b></h1>
<br/>

* This is a Java Application, which helps in building a customized PC.
* It contains data about various products to build a PC (Like RAM, GPU, CPU, Display, etc) and their product details.
* Finally it generates an estimate for building your dream PC, which can be later exported as PDF.
* Some of the Features are:
  > * **Product Details will get updated accurately (When We Run The Scrapper Program, It Scrapes Realtime Data)**
  > * **Easy To Use UI**
  > * **Estimate can be exported as PDF either to the desktop or can be sent to e-mail**
<br/>

* Implemented using
  > * **Java Swing (For Frontend)**
  > * **Oracle DB (For Backend)**
  > * **Externa JARs**
  > > * *Jsoup (For Web Scrapping)*
  > > * *Apache POI (For Reading and Writing Office Format Files)*
  > > * *Aspose Cells (For Generating PDF From XLS)*
  > * **Python Script (For Mailing)**
<br/>

<br/>

<h1 align="center"><b>Important Note? :notebook:</b></h1>

<br/>
<br/>

Please Make Sure to Change These Lines To Make This Program Working Perfectly

* **In src/pcBuild/NewPCBuild.java file**
<br/>

> Change Username and Password Of Your Oracle DB in the following Lines
<br/>

```
ln 104, 205, 243, 270, 289, 3091, 3273
```
<br/>

> Change E-Mail ID and Password Of Your SMTP Mail in the following Lines
<br/>

```
ln 3595, 3622
```
<br/>

> Create table in your database with these query
```

CREATE TABLE PCBPRODUCTS
(	PRID VARCHAR2(6), 
  PNAME VARCHAR2(500),
  PIMG VARCHAR2(500), 
  PDESC VARCHAR2(500), 
  TYPE VARCHAR2(20), 
  PRICE NUMBER(10,2)
);
```
# PC Build User Details Table

```

CREATE TABLE PCBUSERDETAILS
(	MOBILE VARCHAR2(10), 
	NAME VARCHAR2(30), 
  CHECK (length(mobile) = 10), 
	PRIMARY KEY ("MOBILE")
);

```

# PC Build Table

```

CREATE TABLE PCBUILDS
(	BUILDNAME VARCHAR2(50) NOT NULL, 
	MOBILE VARCHAR2(10), 
	PROCESSOR VARCHAR2(6), 
	MOTHERBOARD VARCHAR2(6), 
	STORAGE VARCHAR2(6), 
	RAM VARCHAR2(6), 
	CABINET VARCHAR2(6), 
	COOLER VARCHAR2(6), 
	GPU VARCHAR2(6), 
	POWERSUPPLY VARCHAR2(6), 
	DISPLAY VARCHAR2(6), 
	ESTIMATE NUMBER(20,2), 
	CREATEDAT TIMESTAMP (6), 
	CONSTRAINT "FK_MOBILE" FOREIGN KEY ("MOBILE")
	REFERENCES PCBUSERDETAILS ("MOBILE")
);

```
<br/>
<h1 align="center"><b>How does it works? :thinking:</b></h1>

<br/>
<br/>

# Initial Screen

<table>
<tr>
<td><img src="https://user-images.githubusercontent.com/64122408/118637206-2a53c100-b7f3-11eb-8ac1-9fc9d2fcf9f7.png"/></td>
<td><img src="https://user-images.githubusercontent.com/64122408/118637447-70a92000-b7f3-11eb-8038-7245fb4e5193.png"/></td>
</tr>
<tr>
  <td><h3>Welcome Screen</h3></td>
  <td><h3>Login or Register Screen</h3></td>
</tr>
</table>
<br/>

<table>
<tr>
<td><img src="https://user-images.githubusercontent.com/64122408/118637701-ba920600-b7f3-11eb-8d0e-dec7a603afb6.png"/></td>
<td><img src="https://user-images.githubusercontent.com/64122408/118637837-d72e3e00-b7f3-11eb-998e-c4f767387dff.png"/></td>
</tr>
<tr>
  <td><h3>CPU Selection Screen</h3></td>
  <td><h3>Display Selection Screen</h3></td>
</tr>
</table>
<br/>

<table>
<tr>
<td><img src="https://user-images.githubusercontent.com/64122408/118638117-21afba80-b7f4-11eb-8822-85d747943554.png"/></td>
<td><img src="https://user-images.githubusercontent.com/64122408/118638321-59b6fd80-b7f4-11eb-8233-4f216ea7b6ea.png"/></td>
</tr>
<tr>
  <td><h3>Selected Build Screen</h3></td>
  <td><h3>Export Build Screen</h3></td>
</tr>
</table>
<br/>

<table>
<tr>
<td><img src="https://user-images.githubusercontent.com/64122408/118638517-8ec35000-b7f4-11eb-9989-86ec65b59e6d.png"/></td>
<td><img src="https://user-images.githubusercontent.com/64122408/118638995-0db88880-b7f5-11eb-8473-c685c7ffae69.png"/></td>
</tr>
<tr>
  <td><h3>Estimate (PDF File)</h3></td>
  <td><h3>Estimate To E-Mmail</h3></td>
</tr>
</table>
<br/>

<h1 align="center"><b>NOTE :warning:</b></h1>


# Feel free to fork This Project😇


# CREDITS  
* :white_check_mark:**Karthik Raja**
* :white_check_mark:**Prasath RK**
* :white_check_mark:**Vignesh R**

<p align="left">  
<h3 align="left">Connect with me:</h3>  
<a href="https://instagram.com/vignesh_r_" target="blank"><img align="center" src="https://img.icons8.com/doodle/50/000000/instagram-new.png"/></a>  
</p>  
