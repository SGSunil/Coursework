
*************************************************************************************************************************
*****                   Controller Application for wheelchair						            *****
*************************************************************************************************************************     


TABLE OF CONTENTS
=================

	Document Overview
	Project Information
	Prerequisites 
	Build Information
	Platforms
	Support Information

************************************************************************************
Document Overview
=================
This document provides instructions and steps for:
1. Compiling, builing the source code
2. Running the application from builing the source code.



************************************************************************************
Project Information
===================
Refer to the Week 13 Homework Assignment.



************************************************************************************
Prerequisites - before buidling the code
========================================
1. Simics - target environment
   This is the simulation of the Freescale MPC8641 processor with simulated physical memory that connects to the ADCs, and DACs.

2. 8641-with-notifier.simics” 
   This is the Simics script that starts the simulated processor and memory.

3. Cygwin version 1.7.*:
   for writing down the source code and building the executable with the cross development tools.

4. Cross development tools: 
   These are a set of tools that compile C code to produce PowerPC object code that runs on the    target MPC8641. Please ensure that these tools ar availabe in the cygwin and their path is present
   in the .bashrc file.

5. Please follow the home work instructions and related documents for how to install the tools.




************************************************************************************
Build Information - How to build the source code and run the application
========================================================================

1. Go inside the folder where all the files are extracted.

Buidling up the Team18_Controller
2. Go inside the Team18_Controller Source Code folder.
3. run the make -f Makefile_Controller
4. The above step will result Team18_Controller executable


5. The resulted executables can be run on simics platform given under platforms section.


************************************************************************************
Platforms
=========
1. Tested on simics environment with MPC8641 processor.


************************************************************************************
Support Information:
====================
Sunil Mandhan

ACE Software A Course, Group 18
Week 13, 2011



