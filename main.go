package main
import (
	 "fmt"
)

 

func main() {
	//  创建 区块链 数据结构
	bc := NewBlockchain()
	bc.AddBlock("2019-11-04  xxx")  // 第一笔交易
	bc.AddBlock("2019-11-04  yyy")  // 第二笔交易
	
	for i, block := range bc.blocks {
		fmt.Printf("---------------------区块 %x----------------- \n", i)
		fmt.Printf("父区块地址: %x\n", block.PrevBlockHash)
        fmt.Printf("区块数据: %s\n", block.Data)
        fmt.Printf("区块地址: %x\n", block.Hash)
        fmt.Printf("---------------------------------------------- \n")
	}
	
}