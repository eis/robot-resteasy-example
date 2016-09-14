*** Settings ***

Variables   variables.py

*** Keywords ***

Our simple test
    Test Request     ${TEST_ADDRESS}   /  ${ACCEPT_HEADER}  200

Test with two steps
    Test Request     ${TEST_ADDRESS}   /  ${ACCEPT_HEADER}    200
    Test Request Verify Response    ${TEST_ADDRESS}   /  ${ACCEPT_HEADER}  200     ${ACCEPT_HEADER}
