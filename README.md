# katathursday

Naam anagram
==========

Bij PDOK (lees: Peter) vinden we het leuk om een anagram van de naam te maken. Nu is niet iedereen even goed in puzzelen, dus laten we het automatiseren.

Regels
----
* Namen met meer dan 3 woorden zijn niet leuk, dus het maximum is 3. 
* Elke taal is ok, probeer het echter wel met de core van de taal te doen.

In Namogram staat een voorbeeldimplementatie. In resources staat een woordenlijst, maar het staat je vrij om een eigenlijst te gebruiken.

Tips
----------
* Je wilt misschien de suffe (korte) uit de lijst filteren. a b c, ... zijn ook niet echt woorden.
* Montecarlo werkt waarschijnlijk prima: verdeel de naam in random woorden en check of ze in de woordenlijst voorkomen.
* Als montecarlo werkt (of juist niet) is het ook leuk om netjes alle mogelijkheden af te gaan en die te valideren. 
