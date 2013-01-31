Story: 

The Core should start a process that will periodically notify all modules to start its sanity check


Scenario: Starting the system

Given that system is being initialized
When the Core bundle is activated
Then the module Core creates and starts a new internal of process "module_sanity_check_process"


Scenario: Process is running
Give that process "module_sanity_check_process" was started
When the time event "sanity_check_alarm" were catch
Then the module Core should send notification events to all modules
And those module should start its "internal_sanity_check_process"