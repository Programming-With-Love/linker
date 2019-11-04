package main


import (
	"bytes"
	"crypto/sha256"
	"strconv"
	"time"
)

// Block 由区块头和交易两部分构成
// Timestamp, PrevBlockHash, Hash 属于区块头（block header）
// Timestamp     : 当前时间戳，也就是区块创建的时间
// PrevBlockHash : 前一个块的哈希
// Hash          : 当前块的哈希
// Data          : 区块实际存储的信息，比特币中也就是交易


// 数据类型 Block 区块
type Block struct {
	Timestamp       int64   // 时间戳
	Data  	        []byte  // 数据 
	PrevBlockHash   []byte  // 前一个区块的Hash地址 
	Hash            []byte  // 当前区块地址
}


// 生成 Hash 的方法 
func (b *Block) SetHash() {
	timestamp := []byte(strconv.FormatInt(b.Timestamp, 10))  // 简单转换 
	headers := bytes.Join([][]byte{b.PrevBlockHash, b.Data, timestamp}, []byte{}) // header 连接
	hash := sha256.Sum256(headers)
	b.Hash = hash[:]
}


func NewBlock(data string, prevBlockHash []byte) *Block {
	// 创建 单个区块 
	block := &Block{time.Now().Unix(), []byte(data), prevBlockHash, []byte{}}
	block.SetHash()
	return block
}


//  生成创世块 
func NewGenesisBlock() *Block {
	return NewBlock("2019-11-04 星期一", []byte{})
}

