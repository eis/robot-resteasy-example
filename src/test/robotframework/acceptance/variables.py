import os

TEST_ADDRESS = 'http://headers.jsontest.com'
if 'TEST_ADD' in os.environ:
    TEST_ADDRESS = os.environ['TEST_ADD']

ACCEPT_HEADER = 'application/json'