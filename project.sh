#!/bin/bash

################################################################################
#                              projectV2.sh                                      #
#                                                                              #
# Use this template as the beginning of a new program. Place a short           #
# description of the script here.                                              #
#                                                                              #
# Change History                                                               #
# 05/18/2021  Thibaut Jolive        Original code. This is a template for      #
#                                   creating new Bash shell scripts.           #
#                                   Add new history entries as needed.         #
#                                                                              #
#                                                                              #
################################################################################
################################################################################
################################################################################
#                                                                              #
#  Copyright (C)       2021 Thibaut Jolive                                     #
#                                                                              #
#                                                                              #
#  This program is free software; you can redistribute it and/or modify        #
#  it under the terms of the GNU General Public License as published by        #
#  the Free Software Foundation; either version 2 of the License, or           #
#  (at your option) any later version.                                         #
#                                                                              #
#  This program is distributed in the hope that it will be useful,             #
#  but WITHOUT ANY WARRANTY; without even the implied warranty of              #
#  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the               #
#  GNU General Public License for more details.                                #
#                                                                              #
#  You should have received a copy of the GNU General Public License           #
#  along with this program; if not, write to the Free Software                 #
#  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA   #
#                                                                              #
################################################################################
################################################################################
################################################################################

Help(){
   # Display Help
   echo "Syntax: ./projectV2.sh [-h|c|d] "
   echo
   echo "options:"
   echo "h     Shows this help."
   echo "c     Compile the program."
   echo "d     Generate the javadoc of the program."
   echo
}

compilation=false
javadoc=false

while getopts ":hcd" option; do
	case $option in
		h) # display Help
			Help
			exit;;
		c) #compilation
			compilation=true;;
		d) #javadoc
			javadoc=true;;
		\?) # incorrect option
			echo "Error: Invalid option"
			Help
			exit;;
	esac
done
shift $((OPTIND-1))


if [ $compilation = true ]
then
    echo "Compilation du projet"
	#javac -sourcepath src -d classes -classpath lib/*:classes src/*/*/*.java 
	javac -sourcepath src -d classes -classpath lib/*:classes:classes/* src/backend/*/*.java 
	javac -sourcepath src -d classes -classpath lib/*:classes:classes/* src/*/*.java 
	javac -sourcepath src -d classes -classpath lib/*:classes:classes/* src/*.java 
fi
if [ $javadoc = true ]
then
    echo "Javadoc du projet"
	javadoc -sourcepath src -classpath lib/*:classes:classes/* -d doc frontend -subpackages frontend.user frontend.emprunts frontend.materiel backend.Utilisateurs backend.Emprunts backend.Materiel utils
fi



echo "Execution du projet"
java -classpath lib/*:classes:classes/* Main