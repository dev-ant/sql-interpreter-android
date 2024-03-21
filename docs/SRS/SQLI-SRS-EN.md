<!--This SRS Documentation is based on IEEE 830-1998-->

### Table of Contents

1. [Introduction](#1-introduction)<br>
      1.1. [Purpose](#11-purpose)<br>
      1.2. [Scope](#12-scope)<br>
      1.3. [Definitions, acronyms, and abbreviations](#13-definitions-acronyms-and-abbreviations)<br>
      1.4. [References](#14-references)<br>
      1.5. [Overview](#15-overview)<br>
2. [Overall description](#2-overall-description)<br>
	2.1. [Product perspective](#21-product-perspective)<br>
    2.2. [Product functions](#22-product-functions)<br>
    2.3. [User characteristics](#23-user-characteristics)<br>
    2.4. [Constraints](#24-constraints)<br>
    2.5.[Assumptions and dependencies](#25-assumptions-and-dependencies)<br>
3. [Specific requirements](#3-specific-requirements)<br>
4. [Appendixes](#4-appendixes)<br>
5. [Index](#5-index)<br>
<br>

----


### 1. Introduction
#### 1.1 Purpose
<dd>The purpose of this SRS document is to present a detailed description of SQLI, the Android SQLite interpreter application. Describes the purpose, features, and interface of the application. It also includes what the application will do, what constraints it must operate under, and how the application will respond to external stimuli.</dd>

#### 1.2 Scope
<dd>This project uses Kotlin language to develop Android applications. Also libraries will be used are: Android Jetpack, Compose, JUnit5 etc.
We plan to use GitHub and GitHub Actions for SVC and CI/CD (for play store deployment). Project will implement SQL (based on SQLite) Editor, Console, Create dummy data and App setting (e.g. color theme, font size) features.</dd>

#### 1.3 Definitions, acronyms, and abbreviations
* SQLI : The name of this project, abrreation for SQLite Interpreter

#### 1.4 References
<!--Official Documentations-->
1. Android Developers [Documentation](https://developer.android.com/?gclid=Cj0KCQiAnrOtBhDIARIsAFsSe52g0-UFinZqQ-sfZ49DPFx8jHseounjWQNb0dccohgBRV-is8bvigEaAkP2EALw_wcB&gclsrc=aw.ds')
4. GitHub Actions [Description](https://docs.github.com/ko/actions)
<!--Books-->
2. Park Soo-rae, Ahn Young-gyun, Jeong Jun-wook [Android UI & GUI design](https://product.kyobobook.co.kr/detail/S000001804692)
3. Hong Jeong-ah [Joyce's Android App Programming with Kotlin](https://www.yes24.com/Product/Goods/106730432)
<!--SOF and web post or something else-->


#### 1.5 Overview
<dd>The SQLI can make users learn about SQLite with android devices. It supports all SQLite syntax and implements SQLite console. Some original commands of this app will help users acquire writing query sentence skill more easily.</dd>


---
### 2. Overall description
<dd>'SQLI' can make user read, excute, edit and save sql format files and excute sql console commands in android devices. 'SQLI' console supports SQLite commands and some original commands which is purposed to provide information for users. </dd>

#### 2.2 Product functions

* Execute raw queries
* Display query execution results
* Highlight SQLite keywords
* Load, create, and edit .db files
* Set application font size

#### 2.3 User characteristics


The primary users of this application are assumed to be developers who want to practice SQLite easily on mobile devices.

<!--
References
https://visuresolutions.com/ko/requirements-management-traceability-guide/how-write-system-requirement-documents/
-->