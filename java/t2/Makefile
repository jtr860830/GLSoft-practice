C = javac
R = java

run: build 
	$(R) T2

build: T2.class Pattern.class

T2.class:
	$(C) ./T2.java

Pattern.class:
	$(C) ./Pattern.java

clean:
	rm -rf ./*.class
