# AI Searcher

Engine for solving any kind of problems involving searching, atypical exercise problems encountered by most of
college level AI students.

Algorithms included are:
* Depth first search
* Breadth first search
* Best first search
* A-star search (special case of best first search)

## Architecture

The engine is implemented inside `org.stei.ai.*` package. For solving your specific problem, you must:
* Modeling state by creating a class that extends `AbstractState`
* If you want to use best first search, create a class that implements `Evaluator`

To make your life easier, we've included 3 samples for you to review.

## Getting started

Take a look at `main` method inside `org.stei.sample.Program.java`

``` java
    public static void main(String[] args) throws InterruptedException, IOException {
        // shortest();

        System.out.println();
        eightPuzzle();

        // System.out.println();
        // skiing();
    }
```

If you want to execute the sample, just uncomment `shortest`, `eightpuzzle`, or `skiing` respectively. The shown code
is meant to execute `eightpuzzle` only.

Run the program by executing:
``` shellsession
$ ./gradlew run
```

