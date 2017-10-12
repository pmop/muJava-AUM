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
Please check the [web site][htmldocs].
[htmldocs]: https://sites.google.com/view/useless-mutants/


The projects
---------------
...asasas

Requirements
----------------
 - Java 1.7
 - 

Getting started
----------------
#### Setting up Defects4J
1. Clone Defects4J:
    - `git clone https://github.com/rjust/defects4j`

2. Initialize Defects4J (download the project repositories and external libraries, which are not included in the git repository for size purposes and to avoid redundancies):
    - `cd defects4j`
    - `./init.sh`

3. Add Defects4J's executables to your PATH:
    - `export PATH=$PATH:"path2defects4j"/framework/bin`

#### Using Defects4J
4. Check installation and get information for a specific project (commons lang):
    - `defects4j info -p Lang`

5. Get information for a specific bug (commons lang, bug 1):
    - `defects4j info -p Lang -b 1`

6. Checkout a buggy source code version (commons lang, bug 1, buggy version):
    - `defects4j checkout -p Lang -v 1b -w /tmp/lang_1_buggy`

7. Change to the working directory, compile sources and tests, and run tests:
    - `cd /tmp/lang_1_buggy`
    - `defects4j compile`
    - `defects4j test`

8. More examples of how to use the framework are available in `framework/test`

Publications
------------------
* "Avoiding Useless Mutants"
    Leonardo Fernandes, Márcio Ribeiro, Luiz Carvalho, Rohit Gheyi, Melina Mongiovi, André L. Santos, Ana Cavalcanti, Fabiano Ferrari, José Carlos Maldonado
    GPCE 2017 [[link]][gpce17].

[gpce17]: https://conf.researchr.org/event/gpce-2017/gpce-2017-gpce-2017-avoiding-useless-mutants

Documentation
--------------------
Detailed documentation ...
[html documentation][htmldocs].

[htmldocs]: ...


Directory structure
----------------------
....