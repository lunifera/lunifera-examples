A story is a collection of scenarios

Meta:
	@aCustomTag aValue
	@import jjjk.kkk
!--	@unitMeta 
	
	!--@entityMeta a_link_to_an_existent_Entity
	!--@processMeta a_link_to_an_existent_Process
	!--@unitMeta a_link_to_an_existent_ComponentUnitInstance
Narrative:
	In order to communicate effectively to the business some functionality
	!--As a development_team
	I want to use Behaviour-Driven Development

Scenario: A scenario is a collection of executable steps of different type

	Given a precondition to an event is true
	When a occurrence of the event is triggered
		And another occurrence of the event is triggered
	Then a outcome of the event is showed with these values :
		|col1|col2|col3|
		|value1|value1|value1|


Scenario: Another scenario exploring different combination of events

	Given another precondition
	When a negative event occurs
	Then a the outcome should be captured

	Examples:
	|col1|col2|col3|
	|value1|value1|value1|
