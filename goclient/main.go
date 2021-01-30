package main

import (
	"fmt"
	"log"

	"github.com/TarsCloud/TarsGo/tars"

	"aomi.run/goclient/mqserver"
)

func main() {
	comm := tars.NewCommunicator()
	obj := fmt.Sprintf("example.mqserver.messageObj")
	app := new(mqserver.Message)
	comm.StringToProxy(obj, app)

	ret, err := app.Send("通过golang发送的消息")
	check(ret, err)

	enStr := ""
	ret, err = app.Encode("通过golang发送的加密文本", &enStr)
	check(ret, err)

	ret, err = app.EncodeWithSend("通过golang发送的消息", "通过golang发送的加密文本")
	check(ret, err)

}

func check(status bool, err error) {
	if err != nil {
		fmt.Println(err)
		log.Fatal(err)
	}
	log.Println(status)
}
