# Welcome to Raziel Project 
[![License: GPL v3](https://img.shields.io/badge/License-GPLv3-blue.svg)](https://www.gnu.org/licenses/gpl-3.0)
![master](https://github.com/pikard86/raziel/workflows/Java%20CI%20with%20Maven/badge.svg?branch=master)

A library for private content sharing.
The main goal of this library is to provide a content sharing protocol implementation that preserves privacy.
The library use client-side cryptography and allows to store on remote server private contents.


## Authentication

The only secret shared between client and server is the authentication token, all the other information are encoded
and only the owner can read the content. 
 
## Content 

All the contents can be stored on a remote repository the library encrypts the data and than sends encrypted data to the repository. Data can be shared among users and only the owner and the selected user can access to the content.

 
