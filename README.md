# omiseTest

This is an android application for test task of Omise: https://github.com/omise/challenges/tree/challenge-mobile

Activities are covered with simple UI tests, which run against Robolectric instead of Espresso(wanted to experiment with speed of build).

All the network requests are implemented on coroutines. This is kinda new approach for me(Rx is more close), however it was interesting experience. 

Note: probably I misunderstood the task, but when tried to get data from provided test API it returned error on /donations endpoint.
