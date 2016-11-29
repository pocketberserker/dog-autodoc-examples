## POST /compile.json
Compile posted code.

This API accepts "application/json" in a "Content-Type" header.

#### Request
```
POST /wandbox/api/compile.json
Content-type: application/json

{
  "compiler" : "gcc-head",
  "compiler-option-raw" : "-Dx=hogefuga\n-O3",
  "code" : "#include <iostream>\nint main() { int x = 0; std::cout << \"hoge\" << std::endl; }",
  "options" : "warning,gnu++1y",
  "save" : true
}
```

#### Response
```
200
Access-Control-Allow-Origin: *
Connection: keep-alive
Content-Type: application/json
Date: Tue, 29 Nov 2016 15:47:42 GMT
Server: nginx/1.11.5
Status: HTTP/1.1 200 OK
Transfer-Encoding: chunked
X-Powered-By: CppCMS/1.0.4

{
  "program_output" : "hoge\n",
  "url" : "http://melpon.org/wandbox/permlink/2VOZvTNLD4CuZEpQ",
  "compiler_message" : "prog.cc: In function 'int main()':\n<command-line>:0:3: warning: unused variable 'hogefuga' [-Wunused-variable]\nprog.cc:2:18: note: in expansion of macro 'x'\n int main() { int x = 0; std::cout << \"hoge\" << std::endl; }\n                  ^\n",
  "compiler_output" : null,
  "compiler_error" : "prog.cc: In function 'int main()':\n<command-line>:0:3: warning: unused variable 'hogefuga' [-Wunused-variable]\nprog.cc:2:18: note: in expansion of macro 'x'\n int main() { int x = 0; std::cout << \"hoge\" << std::endl; }\n                  ^\n",
  "permlink" : "2VOZvTNLD4CuZEpQ",
  "status" : "0",
  "program_error" : null,
  "program_message" : "hoge\n",
  "signal" : null
}
```

## GET /permlink/:link
Get a result specified a permanent link :link

#### Request
```
GET /wandbox/api/permlink/2VOZvTNLD4CuZEpQ
```

#### Response
```
200
Access-Control-Allow-Origin: *
Connection: keep-alive
Content-Type: application/json
Date: Tue, 29 Nov 2016 15:47:42 GMT
Server: nginx/1.11.5
Status: HTTP/1.1 200 OK
Transfer-Encoding: chunked
X-Powered-By: CppCMS/1.0.4

{
  "parameter" : {
    "compiler-info" : {
      "switches" : [
        {
          "name" : "warning",
          "display-flags" : "-Wall -Wextra",
          "default" : true,
          "display-name" : "Warnings",
          "type" : "single"
        },
        {
          "name" : "optimize",
          "display-flags" : "-O2 -march=native",
          "default" : false,
          "display-name" : "Optimization",
          "type" : "single"
        },
        {
          "name" : "cpp-verbose",
          "display-flags" : "-v",
          "default" : false,
          "display-name" : "Verbose",
          "type" : "single"
        },
        {
          "default" : "boost-1.62",
          "options" : [
            {
              "display-name" : "Don't Use Boost",
              "display-flags" : "",
              "name" : "boost-nothing"
            },
            {
              "display-name" : "Boost 1.47.0",
              "display-flags" : "-I/usr/local/boost-1.47.0/include",
              "name" : "boost-1.47"
            },
            {
              "display-name" : "Boost 1.48.0",
              "display-flags" : "-I/usr/local/boost-1.48.0/include",
              "name" : "boost-1.48"
            },
            {
              "display-name" : "Boost 1.49.0",
              "display-flags" : "-I/usr/local/boost-1.49.0/include",
              "name" : "boost-1.49"
            },
            {
              "display-name" : "Boost 1.50.0",
              "display-flags" : "-I/usr/local/boost-1.50.0/include",
              "name" : "boost-1.50"
            },
            {
              "display-name" : "Boost 1.51.0",
              "display-flags" : "-I/usr/local/boost-1.51.0/include",
              "name" : "boost-1.51"
            },
            {
              "display-name" : "Boost 1.52.0",
              "display-flags" : "-I/usr/local/boost-1.52.0/include",
              "name" : "boost-1.52"
            },
            {
              "display-name" : "Boost 1.53.0",
              "display-flags" : "-I/usr/local/boost-1.53.0/include",
              "name" : "boost-1.53"
            },
            {
              "display-name" : "Boost 1.54.0",
              "display-flags" : "-I/usr/local/boost-1.54.0/include",
              "name" : "boost-1.54"
            },
            {
              "display-name" : "Boost 1.55.0",
              "display-flags" : "-I/usr/local/boost-1.55.0/include",
              "name" : "boost-1.55"
            },
            {
              "display-name" : "Boost 1.56.0",
              "display-flags" : "-I/usr/local/boost-1.56.0/include",
              "name" : "boost-1.56"
            },
            {
              "display-name" : "Boost 1.57.0",
              "display-flags" : "-I/usr/local/boost-1.57.0/include",
              "name" : "boost-1.57"
            },
            {
              "display-name" : "Boost 1.58.0",
              "display-flags" : "-I/usr/local/boost-1.58.0/include",
              "name" : "boost-1.58"
            },
            {
              "display-name" : "Boost 1.59.0",
              "display-flags" : "-I/usr/local/boost-1.59.0/include",
              "name" : "boost-1.59"
            },
            {
              "display-name" : "Boost 1.60.0",
              "display-flags" : "-I/usr/local/boost-1.60.0/include",
              "name" : "boost-1.60"
            },
            {
              "display-name" : "Boost 1.61.0",
              "display-flags" : "-I/usr/local/boost-1.61.0/include",
              "name" : "boost-1.61"
            },
            {
              "display-name" : "Boost 1.62.0",
              "display-flags" : "-I/usr/local/boost-1.62.0/include",
              "name" : "boost-1.62"
            }
          ],
          "type" : "select"
        },
        {
          "name" : "sprout",
          "display-flags" : "-I/usr/local/sprout",
          "default" : false,
          "display-name" : "Sprout",
          "type" : "single"
        },
        {
          "name" : "msgpack",
          "display-flags" : "-I/usr/local/msgpack/include",
          "default" : false,
          "display-name" : "MessagePack",
          "type" : "single"
        },
        {
          "default" : "gnu++1z",
          "options" : [
            {
              "display-name" : "C++03",
              "display-flags" : "-std=c++98",
              "name" : "c++98"
            },
            {
              "display-name" : "C++03(GNU)",
              "display-flags" : "-std=gnu++98",
              "name" : "gnu++98"
            },
            {
              "display-name" : "C++11",
              "display-flags" : "-std=c++11",
              "name" : "c++11"
            },
            {
              "display-name" : "C++11(GNU)",
              "display-flags" : "-std=gnu++11",
              "name" : "gnu++11"
            },
            {
              "display-name" : "C++14",
              "display-flags" : "-std=c++14",
              "name" : "c++14"
            },
            {
              "display-name" : "C++14(GNU)",
              "display-flags" : "-std=gnu++14",
              "name" : "gnu++14"
            },
            {
              "display-name" : "C++1z",
              "display-flags" : "-std=c++1z",
              "name" : "c++1z"
            },
            {
              "display-name" : "C++1z(GNU)",
              "display-flags" : "-std=gnu++1z",
              "name" : "gnu++1z"
            }
          ],
          "type" : "select"
        },
        {
          "default" : "cpp-no-pedantic",
          "options" : [
            {
              "display-name" : "no pedantic",
              "display-flags" : "",
              "name" : "cpp-no-pedantic"
            },
            {
              "display-name" : "-pedantic",
              "display-flags" : "-pedantic",
              "name" : "cpp-pedantic"
            },
            {
              "display-name" : "-pedantic-errors",
              "display-flags" : "-pedantic-errors",
              "name" : "cpp-pedantic-errors"
            }
          ],
          "type" : "select"
        }
      ],
      "name" : "gcc-head",
      "display-compile-command" : "g++ prog.cc",
      "runtime-option-raw" : false,
      "compiler-option-raw" : true,
      "version" : "7.0.0 20161128 (experimental)",
      "language" : "C++",
      "display-name" : "gcc HEAD"
    },
    "runtime-option-raw" : "",
    "compiler" : "gcc-head",
    "created-at" : "2016-11-29T15:47:42.000+09:00",
    "compiler-option-raw" : "-Dx=hogefuga\n-O3",
    "code" : "#include <iostream>\nint main() { int x = 0; std::cout << \"hoge\" << std::endl; }",
    "options" : "warning,gnu++1y",
    "stdin" : ""
  },
  "result" : {
    "program_output" : "hoge\n",
    "url" : null,
    "compiler_message" : "prog.cc: In function 'int main()':\n<command-line>:0:3: warning: unused variable 'hogefuga' [-Wunused-variable]\nprog.cc:2:18: note: in expansion of macro 'x'\n int main() { int x = 0; std::cout << \"hoge\" << std::endl; }\n                  ^\n",
    "compiler_output" : null,
    "compiler_error" : "prog.cc: In function 'int main()':\n<command-line>:0:3: warning: unused variable 'hogefuga' [-Wunused-variable]\nprog.cc:2:18: note: in expansion of macro 'x'\n int main() { int x = 0; std::cout << \"hoge\" << std::endl; }\n                  ^\n",
    "permlink" : null,
    "status" : "0",
    "program_error" : null,
    "program_message" : "hoge\n",
    "signal" : null
  }
}
```