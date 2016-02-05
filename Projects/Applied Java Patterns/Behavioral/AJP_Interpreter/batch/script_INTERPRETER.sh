#! /bin/bash

START=$(date +%s)

CONT=9
GENERATE=0

#initilization

/home/murat/Masaüstü/eclipse/workspace/AST/Subdue/sgiso -dot /home/murat/Masaüstü/eclipse/workspace/AST/Projects/AJP_Interpreter/outputs/INTERPRETER_outputs/out1.g  -threshold 0.0 /home/murat/Masaüstü/eclipse/workspace/AST/Projects/AJP_Interpreter/inputs/INTERPRETER_inputs/INTERPRETER_1.g /home/murat/Masaüstü/eclipse/workspace/AST/Projects/AJP_Interpreter/source/AJP_Interpreter.g

#first_loop(M combination)
for (( c=4; c<=5; c++ ))
do
/home/murat/Masaüstü/eclipse/workspace/AST/Subdue/sgiso -dot /home/murat/Masaüstü/eclipse/workspace/AST/Projects/AJP_Interpreter/outputs/INTERPRETER_outputs/out$c.g -threshold 0.0 /home/murat/Masaüstü/eclipse/workspace/AST/Projects/AJP_Interpreter/inputs/INTERPRETER_inputs/INTERPRETER_$c.g /home/murat/Masaüstü/eclipse/workspace/AST/Projects/AJP_Interpreter/source/AJP_Interpreter.g

GENERATE=`expr $GENERATE + 1`

declare -i NUMBER
NUMBER=1

declare -i LASTNUMBER
LASTNUMBER=9

S1="/"
S2=""
S3="d"

while read line
do
V=${line:0:1}

if [ "$V" == "$S1" ]; then
continue;
fi
if [ "$V" == "$S2" ]; then
continue;
fi
if [ "$V" == "$S3" ]; then
continue;
fi
if [ "$NUMBER" == "$V" ]; then

if [ "$NUMBER" == "$LASTNUMBER" ]; then 
CONT=`expr $CONT + 1`
break;	
fi
NUMBER=`expr $NUMBER + 1`
else
break;
fi
done</home/murat/Masaüstü/eclipse/workspace/AST/Projects/AJP_Interpreter/outputs/INTERPRETER_outputs/out$c.g
if [ "$CONT" == "$LASTNUMBER" ]; then
continue;
else
break;
fi
done

#second_loop(M X N combination)

CONT=9
for (( c=1; c<=GENERATE; c++ ))
do

for (( d=(((c-1) * 1) + 2); d<=(((c-1) * 1) + (2+2)); d++ ))
do
/home/murat/Masaüstü/eclipse/workspace/AST/Subdue/sgiso -dot /home/murat/Masaüstü/eclipse/workspace/AST/Projects/AJP_Interpreter/outputs/INTERPRETER_outputs/out$d.g -threshold 0.0 /home/murat/Masaüstü/eclipse/workspace/AST/Projects/AJP_Interpreter/inputs/INTERPRETER_inputs/INTERPRETER_$d.g /home/murat/Masaüstü/eclipse/workspace/AST/Projects/AJP_Interpreter/source/AJP_Interpreter.g

declare -i NUMBER
NUMBER=1

declare -i LASTNUMBER
LASTNUMBER=9

S1="/"
S2=""
S3="d"

while read line
do
V=${line:0:1}

if [ "$V" == "$S1" ]; then
continue;
fi
if [ "$V" == "$S2" ]; then
continue;
fi
if [ "$V" == "$S3" ]; then
continue;
fi
if [ "$NUMBER" == "$V" ]; then

if [ "$NUMBER" == "$LASTNUMBER" ]; then 
CONT=`expr $CONT + 1`
break;	
fi
NUMBER=`expr $NUMBER + 1`
else
break;
fi
done</home/murat/Masaüstü/eclipse/workspace/AST/Projects/AJP_Interpreter/outputs/INTERPRETER_outputs/out$d.g
if [ "$CONT" == "$LASTNUMBER" ]; then
continue;
else
break;
fi
done
done

#third_loop(N combination)
for (( c=6; c<=6; c++ ))
do
/home/murat/Masaüstü/eclipse/workspace/AST/Subdue/sgiso -dot /home/murat/Masaüstü/eclipse/workspace/AST/Projects/AJP_Interpreter/outputs/INTERPRETER_outputs/out$c.g -threshold 0.0 /home/murat/Masaüstü/eclipse/workspace/AST/Projects/AJP_Interpreter/inputs/INTERPRETER_inputs/INTERPRETER_$c.g /home/murat/Masaüstü/eclipse/workspace/AST/Projects/AJP_Interpreter/source/AJP_Interpreter.g

declare -i NUMBER
NUMBER=1

declare -i LASTNUMBER
LASTNUMBER=9

S1="/"
S2=""
S3="d"

while read line
do
V=${line:0:1}

if [ "$V" == "$S1" ]; then
continue;
fi
if [ "$V" == "$S2" ]; then
continue;
fi
if [ "$V" == "$S3" ]; then
continue;
fi
if [ "$NUMBER" == "$V" ]; then

if [ "$NUMBER" == "$LASTNUMBER" ]; then 
CONT=`expr $CONT + 1`
break;	
fi
NUMBER=`expr $NUMBER + 1`
else
break;
fi
done</home/murat/Masaüstü/eclipse/workspace/AST/Projects/AJP_Interpreter/outputs/INTERPRETER_outputs/out$c.g
if [ "$CONT" == "$LASTNUMBER" ]; then
continue;
else
break;
fi
done

/home/murat/Masaüstü/eclipse/workspace/AST/Subdue/graph2dot /home/murat/Masaüstü/eclipse/workspace/AST/Projects/AJP_Interpreter/source/AJP_Interpreter.g /home/murat/Masaüstü/eclipse/workspace/AST/Projects/AJP_Interpreter/source/AJP_Interpreter.dot

END=$(date +%s)
DIFF=$(( $END - $START ))
echo "It took $DIFF seconds"


