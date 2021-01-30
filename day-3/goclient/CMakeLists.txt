execute_process(COMMAND go env GOPATH OUTPUT_VARIABLE GOPATH)

string(REGEX REPLACE "\n$" "" GOPATH "${GOPATH}")

include(${GOPATH}/src/github.com/TarsCloud/TarsGo/cmake/tars-tools.cmake)

cmake_minimum_required(VERSION 2.8)
set(CMAKE_EXE_LINKER_FLAGS "-static")

project(goclient Go) # select GO compile
# add_subdirectory(adminer)

gen_server(example goclient)


# go mod init
# mkdir build
# cd build
# cmake .. or cmake .. -DTARS_WEB_HOST=http://127.0.0.1:3000 -DTARS_TOKEN=7af79dcef90872d35cba14e274da59f8a903f8fd
# make
