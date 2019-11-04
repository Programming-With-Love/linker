package main



// BlockChain 是一个 Block 指针数组
type BlockChain struct {
	blocks []*Block
}



// 包含创世区块的 区块链生成 
func NewBlockchain() *BlockChain {
	return &BlockChain{[]*Block{NewGenesisBlock()}}
}



// 为区块链  增加一个区块 
func (bc *BlockChain) AddBlock(data string) {
	prevBlock := bc.blocks[len(bc.blocks) - 1] // 最新一个区块 的Hash 作为 当前区块父Hash
	newBlock := NewBlock(data, prevBlock.Hash)
	bc.blocks = append(bc.blocks, newBlock)   // 原来基础上 再增加一个区块
}

