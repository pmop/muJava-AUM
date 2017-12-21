MuJava-AUM
===========

Original MuJava README:
-------------------------
Mutation system for Java programs, including OO mutation operators.

Please see muJava's homepage for detail: http://cs.gmu.edu/~offutt/mujava

-------------------------------------------------------------------------

MuJava-AUM -- version 0.0.1
----------------
MuJava-AUM is similar to original MuJava, but with support to Avoid Useless Mutants.
Please check https://sites.google.com/view/useless-mutants/


Requirements
----------------
 - Java > 1.7
 - 

Getting started
----------------
#### Setting up MuJava-AUM
1. Clone MuJava-AUM:
    - `git clone https://github.com/Nimrod-Easy-Lab/muJava-AUM.git`

2. Initialize MuJava-AUM (install the dependencies into your local maven repository):
    - `cd MuJava-AUM`
    - `mvn install:install-file -Dfile=lib/tools.jar -DgroupId=com.sun  -DartifactId=tools -Dversion=1.7.0.13 -Dpackaging=jar`
    - `mvn install:install-file -Dfile=lib/openjava.jar -DgroupId=ojc.openjava  -DartifactId=ojc-openjava -Dversion=1.0 -Dpackaging=jar`
    
    - `mvn compile`


#### Using MuJava-AUM
5. Change the file mujava.config to point out to examples/session1/ folder
    - `MuJava_HOME=<absolute-path>/muJava-AUM/examples/session1`
6. Compile the source files from examples/session1/src/ directory
    - `javac examples/session1/src/*.java -d examples/session1/classes/`

7. Execute the main file:
    - `mvn exec:java -Dexec.mainClass="mujava.gui.GenMutantsMain"`

#### Generating jar

First, in `src/test/resources/mutants/session`, make the folders (if not exists): classes, result, src and testset.

After that, run: `mvn install`. The jar will be in *target* folder, with *jar-with-dependencies.jar* appended.


Publications
------------------
* "Avoiding Useless Mutants" - 
    Leonardo Fernandes, Márcio Ribeiro, Luiz Carvalho, Rohit Gheyi, Melina Mongiovi, André L. Santos, Ana Cavalcanti, Fabiano Ferrari, José Carlos Maldonado
    (GPCE 2017) [[link]][gpce17].

[gpce17]: https://conf.researchr.org/event/gpce-2017/gpce-2017-gpce-2017-avoiding-useless-mutants

Documentation
--------------------
Detailed documentation ...
[html documentation][htmldocs].

[htmldocs]: https://github.com/Nimrod-Easy-Lab/muJava-AUM


Obs.
----------------------

