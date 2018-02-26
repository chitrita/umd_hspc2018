# University of Maryland High School Programming Competition (HSPC) 2018
Repository of problems and their full solutions from the 2018 UMD High School Programming Competition ([website](http://www.cs.umd.edu/Outreach/hsContest18/), [final scoreboard](http://contest.cs.umd.edu/hscontest/summary.html)).  

## `/docs`
* 2018-problem-writeup.pdf: a whitepaper describing each of eight competition problems, along with input/output format and some sample input/output.
* 2018-solutions.pptx/pdf: high-level solutions for the problems; full Java implementations can be found in `/src` below.
* 2018-introduction.pptx: intro presentation given to students before the practice round.


## `/src`
This is a single Eclipse project containing eight packages, one per problem used in the main competition.  Note that the actual Java files used in the competition were slightly modified (to remove the leading package statement at the header of each Java file).  The files are otherwise unchanged.  To run each problem, either:

* Run the Java file as-is in Eclipse, and feed in a file or manual input via STDIN; or
* Compile with `javac ProblemName.java` and then run with `java ProblemName < Input.in` from the command line (this is more in line with how the competition tested solutions).

Problems contain both the test skeleton (`ProblemName.java`) given to teams and a solution (`ProblemName_solution.java`) created by one of the competition judges.  Caveat emptor on the solutions.

## `/tests`
Secret test inputs and matching outputs that were run through PC2 during the actual competition.  We note that a few problems were graded mostly manually - specifically P2, P5, and P6, due to some issues with diffing their outputs.

## Acknowledgments
### Problem Creators, Solution Fixers, and Judges:

* [Ahmed Abdelkader](http://www.cs.umd.edu/~akader/)
* [John P Dickerson](http://jpdickerson.com/)
* [Tom Goldstein](https://www.cs.umd.edu/~tomg/)
* [Evan Golub](http://www.cs.umd.edu/~egolub/professional.shtml)
* [Dave Mount](https://www.cs.umd.edu/users/mount/)

### Motivation for some of the Problems:

* [Benelux Algorithm Programming Contest Leiden](http://2010.bapc.eu/)
* [University of Saskatchewan Programming Contest](http://programmingcontest.usask.ca/)
* [Daniel Fischer](https://stackoverflow.com/questions/9625663/calculating-and-printing-the-nth-prime-number)
