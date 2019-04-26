#!/usr/bin/env bash

start_runcrud()
{
    ./runcrud.sh
}

start_firefox()
{
    firefox http://localhost:8080/crud/v1/task/getTasks
}

start_chrome()
{
    google-chrome http://localhost:8080/crud/v1/task/getTasks
}

script_completed()
{
    echo "Runcrud.sh execution completed."
}

script_failed()
{
    echo "Runcrud.sh execution failed."
}

firefox_started()
{
    echo "Firefox successfully started."
}

chrome_started()
{
    echo "Google Chrome successfully started."
}

chrome_failed()
{
    echo "Google Chrome launch failed."
}

firefox_failed()
{
    echo "Firefox launch failed."
    echo "Trying launch Google Chrome."
    if start_chrome; then
        chrome_started
    else
        chrome_failed
    fi
}

if start_runcrud; then
    script_completed
else
    script_failed
fi

if start_firefox; then
    firefox_started
else
    firefox_failed
fi