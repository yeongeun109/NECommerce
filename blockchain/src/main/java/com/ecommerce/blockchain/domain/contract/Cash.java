package com.ecommerce.blockchain.domain.contract;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class Cash extends Contract {
    public static final String BINARY = "{\n"
            + "\t\"linkReferences\": {\n"
            + "\t\t\"ERC20Lib.sol\": {\n"
            + "\t\t\t\"ERC20Lib\": [\n"
            + "\t\t\t\t{\n"
            + "\t\t\t\t\t\"length\": 20,\n"
            + "\t\t\t\t\t\"start\": 219\n"
            + "\t\t\t\t},\n"
            + "\t\t\t\t{\n"
            + "\t\t\t\t\t\"length\": 20,\n"
            + "\t\t\t\t\t\"start\": 1301\n"
            + "\t\t\t\t},\n"
            + "\t\t\t\t{\n"
            + "\t\t\t\t\t\"length\": 20,\n"
            + "\t\t\t\t\t\"start\": 1497\n"
            + "\t\t\t\t},\n"
            + "\t\t\t\t{\n"
            + "\t\t\t\t\t\"length\": 20,\n"
            + "\t\t\t\t\t\"start\": 1685\n"
            + "\t\t\t\t},\n"
            + "\t\t\t\t{\n"
            + "\t\t\t\t\t\"length\": 20,\n"
            + "\t\t\t\t\t\"start\": 1957\n"
            + "\t\t\t\t},\n"
            + "\t\t\t\t{\n"
            + "\t\t\t\t\t\"length\": 20,\n"
            + "\t\t\t\t\t\"start\": 2089\n"
            + "\t\t\t\t}\n"
            + "\t\t\t]\n"
            + "\t\t}\n"
            + "\t},\n"
            + "\t\"object\": \"60c0604052600b60808190527f53696d706c65546f6b656e00000000000000000000000000000000000000000060a090815261003e916003919061012d565b506040805180820190915260038082527f53494d000000000000000000000000000000000000000000000000000000000060209092019182526100839160049161012d565b50601260055561271060065534801561009b57600080fd5b50600654604080517f43505a750000000000000000000000000000000000000000000000000000000081526000600482018190526024820193909352905173__ERC20Lib.sol:ERC20Lib_________________926343505a759260448082019391829003018186803b15801561011057600080fd5b505af4158015610124573d6000803e3d6000fd5b505050506101c8565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061016e57805160ff191683800117855561019b565b8280016001018555821561019b579182015b8281111561019b578251825591602001919060010190610180565b506101a79291506101ab565b5090565b6101c591905b808211156101a757600081556001016101b1565b90565b6106b7806101d76000396000f3006080604052600436106100a35763ffffffff7c010000000000000000000000000000000000000000000000000000000060003504166306fdde0381146100a8578063095ea7b31461013257806318160ddd1461016a57806323b872dd146101915780632ff2e9dc146101bb578063313ce567146101d057806370a08231146101e557806395d89b4114610206578063a9059cbb1461021b578063dd62ed3e1461023f575b600080fd5b3480156100b457600080fd5b506100bd610266565b6040805160208082528351818301528351919283929083019185019080838360005b838110156100f75781810151838201526020016100df565b50505050905090810190601f1680156101245780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b34801561013e57600080fd5b50610156600160a060020a03600435166024356102f4565b604080519115158252519081900360200190f35b34801561017657600080fd5b5061017f6103aa565b60408051918252519081900360200190f35b34801561019d57600080fd5b50610156600160a060020a03600435811690602435166044356103b0565b3480156101c757600080fd5b5061017f61046f565b3480156101dc57600080fd5b5061017f610475565b3480156101f157600080fd5b5061017f600160a060020a036004351661047b565b34801561021257600080fd5b506100bd610529565b34801561022757600080fd5b50610156600160a060020a0360043516602435610584565b34801561024b57600080fd5b5061017f600160a060020a0360043581169060243516610607565b6003805460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156102ec5780601f106102c1576101008083540402835291602001916102ec565b820191906000526020600020905b8154815290600101906020018083116102cf57829003601f168201915b505050505081565b604080517f5bada338000000000000000000000000000000000000000000000000000000008152600060048201819052600160a060020a038516602483015260448201849052915173__ERC20Lib.sol:ERC20Lib_________________91635bada338916064808301926020929190829003018186803b15801561037757600080fd5b505af415801561038b573d6000803e3d6000fd5b505050506040513d60208110156103a157600080fd5b50519392505050565b60025490565b604080517f45008797000000000000000000000000000000000000000000000000000000008152600060048201819052600160a060020a0380871660248401528516604483015260648201849052915173__ERC20Lib.sol:ERC20Lib_________________916345008797916084808301926020929190829003018186803b15801561043b57600080fd5b505af415801561044f573d6000803e3d6000fd5b505050506040513d602081101561046557600080fd5b5051949350505050565b60065481565b60055481565b604080517fd1717fd4000000000000000000000000000000000000000000000000000000008152600060048201819052600160a060020a0384166024830152915173__ERC20Lib.sol:ERC20Lib_________________9163d1717fd4916044808301926020929190829003018186803b1580156104f757600080fd5b505af415801561050b573d6000803e3d6000fd5b505050506040513d602081101561052157600080fd5b505192915050565b6004805460408051602060026001851615610100026000190190941693909304601f810184900484028201840190925281815292918301828280156102ec5780601f106102c1576101008083540402835291602001916102ec565b604080517f2d0277b9000000000000000000000000000000000000000000000000000000008152600060048201819052600160a060020a038516602483015260448201849052915173__ERC20Lib.sol:ERC20Lib_________________91632d0277b9916064808301926020929190829003018186803b15801561037757600080fd5b604080517f88fd6510000000000000000000000000000000000000000000000000000000008152600060048201819052600160a060020a03808616602484015284166044830152915173__ERC20Lib.sol:ERC20Lib_________________916388fd6510916064808301926020929190829003018186803b15801561037757600080fd00a165627a7a72305820613e6e6017c8c92d9b746e44f77741881135b0ac2d83154525a72f982a42dc280029\",\n"
            + "\t\"opcodes\": \"PUSH1 0xC0 PUSH1 0x40 MSTORE PUSH1 0xB PUSH1 0x80 DUP2 SWAP1 MSTORE PUSH32 0x53696D706C65546F6B656E000000000000000000000000000000000000000000 PUSH1 0xA0 SWAP1 DUP2 MSTORE PUSH2 0x3E SWAP2 PUSH1 0x3 SWAP2 SWAP1 PUSH2 0x12D JUMP JUMPDEST POP PUSH1 0x40 DUP1 MLOAD DUP1 DUP3 ADD SWAP1 SWAP2 MSTORE PUSH1 0x3 DUP1 DUP3 MSTORE PUSH32 0x53494D0000000000000000000000000000000000000000000000000000000000 PUSH1 0x20 SWAP1 SWAP3 ADD SWAP2 DUP3 MSTORE PUSH2 0x83 SWAP2 PUSH1 0x4 SWAP2 PUSH2 0x12D JUMP JUMPDEST POP PUSH1 0x12 PUSH1 0x5 SSTORE PUSH2 0x2710 PUSH1 0x6 SSTORE CALLVALUE DUP1 ISZERO PUSH2 0x9B JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH1 0x6 SLOAD PUSH1 0x40 DUP1 MLOAD PUSH32 0x43505A7500000000000000000000000000000000000000000000000000000000 DUP2 MSTORE PUSH1 0x0 PUSH1 0x4 DUP3 ADD DUP2 SWAP1 MSTORE PUSH1 0x24 DUP3 ADD SWAP4 SWAP1 SWAP4 MSTORE SWAP1 MLOAD PUSH20 0x0 SWAP3 PUSH4 0x43505A75 SWAP3 PUSH1 0x44 DUP1 DUP3 ADD SWAP4 SWAP2 DUP3 SWAP1 SUB ADD DUP2 DUP7 DUP1 EXTCODESIZE ISZERO DUP1 ISZERO PUSH2 0x110 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP GAS DELEGATECALL ISZERO DUP1 ISZERO PUSH2 0x124 JUMPI RETURNDATASIZE PUSH1 0x0 DUP1 RETURNDATACOPY RETURNDATASIZE PUSH1 0x0 REVERT JUMPDEST POP POP POP POP PUSH2 0x1C8 JUMP JUMPDEST DUP3 DUP1 SLOAD PUSH1 0x1 DUP2 PUSH1 0x1 AND ISZERO PUSH2 0x100 MUL SUB AND PUSH1 0x2 SWAP1 DIV SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 SWAP1 PUSH1 0x1F ADD PUSH1 0x20 SWAP1 DIV DUP2 ADD SWAP3 DUP3 PUSH1 0x1F LT PUSH2 0x16E JUMPI DUP1 MLOAD PUSH1 0xFF NOT AND DUP4 DUP1 ADD OR DUP6 SSTORE PUSH2 0x19B JUMP JUMPDEST DUP3 DUP1 ADD PUSH1 0x1 ADD DUP6 SSTORE DUP3 ISZERO PUSH2 0x19B JUMPI SWAP2 DUP3 ADD JUMPDEST DUP3 DUP2 GT ISZERO PUSH2 0x19B JUMPI DUP3 MLOAD DUP3 SSTORE SWAP2 PUSH1 0x20 ADD SWAP2 SWAP1 PUSH1 0x1 ADD SWAP1 PUSH2 0x180 JUMP JUMPDEST POP PUSH2 0x1A7 SWAP3 SWAP2 POP PUSH2 0x1AB JUMP JUMPDEST POP SWAP1 JUMP JUMPDEST PUSH2 0x1C5 SWAP2 SWAP1 JUMPDEST DUP1 DUP3 GT ISZERO PUSH2 0x1A7 JUMPI PUSH1 0x0 DUP2 SSTORE PUSH1 0x1 ADD PUSH2 0x1B1 JUMP JUMPDEST SWAP1 JUMP JUMPDEST PUSH2 0x6B7 DUP1 PUSH2 0x1D7 PUSH1 0x0 CODECOPY PUSH1 0x0 RETURN STOP PUSH1 0x80 PUSH1 0x40 MSTORE PUSH1 0x4 CALLDATASIZE LT PUSH2 0xA3 JUMPI PUSH4 0xFFFFFFFF PUSH29 0x100000000000000000000000000000000000000000000000000000000 PUSH1 0x0 CALLDATALOAD DIV AND PUSH4 0x6FDDE03 DUP2 EQ PUSH2 0xA8 JUMPI DUP1 PUSH4 0x95EA7B3 EQ PUSH2 0x132 JUMPI DUP1 PUSH4 0x18160DDD EQ PUSH2 0x16A JUMPI DUP1 PUSH4 0x23B872DD EQ PUSH2 0x191 JUMPI DUP1 PUSH4 0x2FF2E9DC EQ PUSH2 0x1BB JUMPI DUP1 PUSH4 0x313CE567 EQ PUSH2 0x1D0 JUMPI DUP1 PUSH4 0x70A08231 EQ PUSH2 0x1E5 JUMPI DUP1 PUSH4 0x95D89B41 EQ PUSH2 0x206 JUMPI DUP1 PUSH4 0xA9059CBB EQ PUSH2 0x21B JUMPI DUP1 PUSH4 0xDD62ED3E EQ PUSH2 0x23F JUMPI JUMPDEST PUSH1 0x0 DUP1 REVERT JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0xB4 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0xBD PUSH2 0x266 JUMP JUMPDEST PUSH1 0x40 DUP1 MLOAD PUSH1 0x20 DUP1 DUP3 MSTORE DUP4 MLOAD DUP2 DUP4 ADD MSTORE DUP4 MLOAD SWAP2 SWAP3 DUP4 SWAP3 SWAP1 DUP4 ADD SWAP2 DUP6 ADD SWAP1 DUP1 DUP4 DUP4 PUSH1 0x0 JUMPDEST DUP4 DUP2 LT ISZERO PUSH2 0xF7 JUMPI DUP2 DUP2 ADD MLOAD DUP4 DUP3 ADD MSTORE PUSH1 0x20 ADD PUSH2 0xDF JUMP JUMPDEST POP POP POP POP SWAP1 POP SWAP1 DUP2 ADD SWAP1 PUSH1 0x1F AND DUP1 ISZERO PUSH2 0x124 JUMPI DUP1 DUP3 SUB DUP1 MLOAD PUSH1 0x1 DUP4 PUSH1 0x20 SUB PUSH2 0x100 EXP SUB NOT AND DUP2 MSTORE PUSH1 0x20 ADD SWAP2 POP JUMPDEST POP SWAP3 POP POP POP PUSH1 0x40 MLOAD DUP1 SWAP2 SUB SWAP1 RETURN JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x13E JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x156 PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB PUSH1 0x4 CALLDATALOAD AND PUSH1 0x24 CALLDATALOAD PUSH2 0x2F4 JUMP JUMPDEST PUSH1 0x40 DUP1 MLOAD SWAP2 ISZERO ISZERO DUP3 MSTORE MLOAD SWAP1 DUP2 SWAP1 SUB PUSH1 0x20 ADD SWAP1 RETURN JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x176 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x17F PUSH2 0x3AA JUMP JUMPDEST PUSH1 0x40 DUP1 MLOAD SWAP2 DUP3 MSTORE MLOAD SWAP1 DUP2 SWAP1 SUB PUSH1 0x20 ADD SWAP1 RETURN JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x19D JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x156 PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB PUSH1 0x4 CALLDATALOAD DUP2 AND SWAP1 PUSH1 0x24 CALLDATALOAD AND PUSH1 0x44 CALLDATALOAD PUSH2 0x3B0 JUMP JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x1C7 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x17F PUSH2 0x46F JUMP JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x1DC JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x17F PUSH2 0x475 JUMP JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x1F1 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x17F PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB PUSH1 0x4 CALLDATALOAD AND PUSH2 0x47B JUMP JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x212 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0xBD PUSH2 0x529 JUMP JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x227 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x156 PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB PUSH1 0x4 CALLDATALOAD AND PUSH1 0x24 CALLDATALOAD PUSH2 0x584 JUMP JUMPDEST CALLVALUE DUP1 ISZERO PUSH2 0x24B JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP PUSH2 0x17F PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB PUSH1 0x4 CALLDATALOAD DUP2 AND SWAP1 PUSH1 0x24 CALLDATALOAD AND PUSH2 0x607 JUMP JUMPDEST PUSH1 0x3 DUP1 SLOAD PUSH1 0x40 DUP1 MLOAD PUSH1 0x20 PUSH1 0x2 PUSH1 0x1 DUP6 AND ISZERO PUSH2 0x100 MUL PUSH1 0x0 NOT ADD SWAP1 SWAP5 AND SWAP4 SWAP1 SWAP4 DIV PUSH1 0x1F DUP2 ADD DUP5 SWAP1 DIV DUP5 MUL DUP3 ADD DUP5 ADD SWAP1 SWAP3 MSTORE DUP2 DUP2 MSTORE SWAP3 SWAP2 DUP4 ADD DUP3 DUP3 DUP1 ISZERO PUSH2 0x2EC JUMPI DUP1 PUSH1 0x1F LT PUSH2 0x2C1 JUMPI PUSH2 0x100 DUP1 DUP4 SLOAD DIV MUL DUP4 MSTORE SWAP2 PUSH1 0x20 ADD SWAP2 PUSH2 0x2EC JUMP JUMPDEST DUP3 ADD SWAP2 SWAP1 PUSH1 0x0 MSTORE PUSH1 0x20 PUSH1 0x0 KECCAK256 SWAP1 JUMPDEST DUP2 SLOAD DUP2 MSTORE SWAP1 PUSH1 0x1 ADD SWAP1 PUSH1 0x20 ADD DUP1 DUP4 GT PUSH2 0x2CF JUMPI DUP3 SWAP1 SUB PUSH1 0x1F AND DUP3 ADD SWAP2 JUMPDEST POP POP POP POP POP DUP2 JUMP JUMPDEST PUSH1 0x40 DUP1 MLOAD PUSH32 0x5BADA33800000000000000000000000000000000000000000000000000000000 DUP2 MSTORE PUSH1 0x0 PUSH1 0x4 DUP3 ADD DUP2 SWAP1 MSTORE PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB DUP6 AND PUSH1 0x24 DUP4 ADD MSTORE PUSH1 0x44 DUP3 ADD DUP5 SWAP1 MSTORE SWAP2 MLOAD PUSH20 0x0 SWAP2 PUSH4 0x5BADA338 SWAP2 PUSH1 0x64 DUP1 DUP4 ADD SWAP3 PUSH1 0x20 SWAP3 SWAP2 SWAP1 DUP3 SWAP1 SUB ADD DUP2 DUP7 DUP1 EXTCODESIZE ISZERO DUP1 ISZERO PUSH2 0x377 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP GAS DELEGATECALL ISZERO DUP1 ISZERO PUSH2 0x38B JUMPI RETURNDATASIZE PUSH1 0x0 DUP1 RETURNDATACOPY RETURNDATASIZE PUSH1 0x0 REVERT JUMPDEST POP POP POP POP PUSH1 0x40 MLOAD RETURNDATASIZE PUSH1 0x20 DUP2 LT ISZERO PUSH2 0x3A1 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP MLOAD SWAP4 SWAP3 POP POP POP JUMP JUMPDEST PUSH1 0x2 SLOAD SWAP1 JUMP JUMPDEST PUSH1 0x40 DUP1 MLOAD PUSH32 0x4500879700000000000000000000000000000000000000000000000000000000 DUP2 MSTORE PUSH1 0x0 PUSH1 0x4 DUP3 ADD DUP2 SWAP1 MSTORE PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB DUP1 DUP8 AND PUSH1 0x24 DUP5 ADD MSTORE DUP6 AND PUSH1 0x44 DUP4 ADD MSTORE PUSH1 0x64 DUP3 ADD DUP5 SWAP1 MSTORE SWAP2 MLOAD PUSH20 0x0 SWAP2 PUSH4 0x45008797 SWAP2 PUSH1 0x84 DUP1 DUP4 ADD SWAP3 PUSH1 0x20 SWAP3 SWAP2 SWAP1 DUP3 SWAP1 SUB ADD DUP2 DUP7 DUP1 EXTCODESIZE ISZERO DUP1 ISZERO PUSH2 0x43B JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP GAS DELEGATECALL ISZERO DUP1 ISZERO PUSH2 0x44F JUMPI RETURNDATASIZE PUSH1 0x0 DUP1 RETURNDATACOPY RETURNDATASIZE PUSH1 0x0 REVERT JUMPDEST POP POP POP POP PUSH1 0x40 MLOAD RETURNDATASIZE PUSH1 0x20 DUP2 LT ISZERO PUSH2 0x465 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP MLOAD SWAP5 SWAP4 POP POP POP POP JUMP JUMPDEST PUSH1 0x6 SLOAD DUP2 JUMP JUMPDEST PUSH1 0x5 SLOAD DUP2 JUMP JUMPDEST PUSH1 0x40 DUP1 MLOAD PUSH32 0xD1717FD400000000000000000000000000000000000000000000000000000000 DUP2 MSTORE PUSH1 0x0 PUSH1 0x4 DUP3 ADD DUP2 SWAP1 MSTORE PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB DUP5 AND PUSH1 0x24 DUP4 ADD MSTORE SWAP2 MLOAD PUSH20 0x0 SWAP2 PUSH4 0xD1717FD4 SWAP2 PUSH1 0x44 DUP1 DUP4 ADD SWAP3 PUSH1 0x20 SWAP3 SWAP2 SWAP1 DUP3 SWAP1 SUB ADD DUP2 DUP7 DUP1 EXTCODESIZE ISZERO DUP1 ISZERO PUSH2 0x4F7 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP GAS DELEGATECALL ISZERO DUP1 ISZERO PUSH2 0x50B JUMPI RETURNDATASIZE PUSH1 0x0 DUP1 RETURNDATACOPY RETURNDATASIZE PUSH1 0x0 REVERT JUMPDEST POP POP POP POP PUSH1 0x40 MLOAD RETURNDATASIZE PUSH1 0x20 DUP2 LT ISZERO PUSH2 0x521 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST POP MLOAD SWAP3 SWAP2 POP POP JUMP JUMPDEST PUSH1 0x4 DUP1 SLOAD PUSH1 0x40 DUP1 MLOAD PUSH1 0x20 PUSH1 0x2 PUSH1 0x1 DUP6 AND ISZERO PUSH2 0x100 MUL PUSH1 0x0 NOT ADD SWAP1 SWAP5 AND SWAP4 SWAP1 SWAP4 DIV PUSH1 0x1F DUP2 ADD DUP5 SWAP1 DIV DUP5 MUL DUP3 ADD DUP5 ADD SWAP1 SWAP3 MSTORE DUP2 DUP2 MSTORE SWAP3 SWAP2 DUP4 ADD DUP3 DUP3 DUP1 ISZERO PUSH2 0x2EC JUMPI DUP1 PUSH1 0x1F LT PUSH2 0x2C1 JUMPI PUSH2 0x100 DUP1 DUP4 SLOAD DIV MUL DUP4 MSTORE SWAP2 PUSH1 0x20 ADD SWAP2 PUSH2 0x2EC JUMP JUMPDEST PUSH1 0x40 DUP1 MLOAD PUSH32 0x2D0277B900000000000000000000000000000000000000000000000000000000 DUP2 MSTORE PUSH1 0x0 PUSH1 0x4 DUP3 ADD DUP2 SWAP1 MSTORE PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB DUP6 AND PUSH1 0x24 DUP4 ADD MSTORE PUSH1 0x44 DUP3 ADD DUP5 SWAP1 MSTORE SWAP2 MLOAD PUSH20 0x0 SWAP2 PUSH4 0x2D0277B9 SWAP2 PUSH1 0x64 DUP1 DUP4 ADD SWAP3 PUSH1 0x20 SWAP3 SWAP2 SWAP1 DUP3 SWAP1 SUB ADD DUP2 DUP7 DUP1 EXTCODESIZE ISZERO DUP1 ISZERO PUSH2 0x377 JUMPI PUSH1 0x0 DUP1 REVERT JUMPDEST PUSH1 0x40 DUP1 MLOAD PUSH32 0x88FD651000000000000000000000000000000000000000000000000000000000 DUP2 MSTORE PUSH1 0x0 PUSH1 0x4 DUP3 ADD DUP2 SWAP1 MSTORE PUSH1 0x1 PUSH1 0xA0 PUSH1 0x2 EXP SUB DUP1 DUP7 AND PUSH1 0x24 DUP5 ADD MSTORE DUP5 AND PUSH1 0x44 DUP4 ADD MSTORE SWAP2 MLOAD PUSH20 0x0 SWAP2 PUSH4 0x88FD6510 SWAP2 PUSH1 0x64 DUP1 DUP4 ADD SWAP3 PUSH1 0x20 SWAP3 SWAP2 SWAP1 DUP3 SWAP1 SUB ADD DUP2 DUP7 DUP1 EXTCODESIZE ISZERO DUP1 ISZERO PUSH2 0x377 JUMPI PUSH1 0x0 DUP1 REVERT STOP LOG1 PUSH6 0x627A7A723058 KECCAK256 PUSH2 0x3E6E PUSH1 0x17 0xc8 0xc9 0x2d SWAP12 PUSH21 0x6E44F77741881135B0AC2D83154525A72F982A42DC 0x28 STOP 0x29 \",\n"
            + "\t\"sourceMap\": \"380:34:0:-;265:1359;380:34;;265:1359;380:34;;;;;;;;;;;;;;:::i;:::-;-1:-1:-1;421:28:0;;;;;;;;;;;;;;;;;;;;;;;;;;:::i;:::-;;482:2;456:28;;523:5;491:37;;537:70;8:9:-1;5:2;;;30:1;27;20:12;5:2;-1:-1;584:14:0;;573:26;;;;;;:5;:26;;;;;;;;;;;;;;;:10;;;;:26;;;;;;;;;;;:10;:26;;;5:2:-1;;;;30:1;27;20:12;5:2;573:26:0;;;;8:9:-1;5:2;;;45:16;42:1;39;24:38;77:16;74:1;67:27;5:2;573:26:0;;;;265:1359;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;-1:-1:-1;265:1359:0;;;-1:-1:-1;265:1359:0;:::i;:::-;;;:::o;:::-;;;;;;;;;;;;;;;;;;;;:::o;:::-;;;;;;;\"\n"
            + "}";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final String FUNC_INITIAL_SUPPLY = "INITIAL_SUPPLY";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final Event TRANSFER_EVENT = new Event("Transfer", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    public static final Event APPROVAL_EVENT = new Event("Approval", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Address>(true) {}, new TypeReference<Address>(true) {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected Cash(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected Cash(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected Cash(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected Cash(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<String> name() {
        final Function function = new Function(FUNC_NAME, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger value) {
        final Function function = new Function(
                FUNC_APPROVE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, spender), 
                new org.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        final Function function = new Function(FUNC_TOTALSUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger value) {
        final Function function = new Function(
                FUNC_TRANSFERFROM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, from), 
                new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> INITIAL_SUPPLY() {
        final Function function = new Function(FUNC_INITIAL_SUPPLY, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        final Function function = new Function(FUNC_DECIMALS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> balanceOf(String who) {
        final Function function = new Function(FUNC_BALANCEOF, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, who)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<String> symbol() {
        final Function function = new Function(FUNC_SYMBOL, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}));
        return executeRemoteCallSingleValueReturn(function, String.class);
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String to, BigInteger value) {
        final Function function = new Function(
                FUNC_TRANSFER, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, to), 
                new org.web3j.abi.datatypes.generated.Uint256(value)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<BigInteger> allowance(String owner, String spender) {
        final Function function = new Function(FUNC_ALLOWANCE, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner), 
                new org.web3j.abi.datatypes.Address(160, spender)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses = new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<TransferEventResponse> transferEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, TransferEventResponse>() {
            @Override
            public TransferEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(TRANSFER_EVENT, log);
                TransferEventResponse typedResponse = new TransferEventResponse();
                typedResponse.log = log;
                typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<TransferEventResponse> transferEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(TRANSFER_EVENT));
        return transferEventFlowable(filter);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses = new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(new io.reactivex.functions.Function<Log, ApprovalEventResponse>() {
            @Override
            public ApprovalEventResponse apply(Log log) {
                Contract.EventValuesWithLog eventValues = extractEventParametersWithLog(APPROVAL_EVENT, log);
                ApprovalEventResponse typedResponse = new ApprovalEventResponse();
                typedResponse.log = log;
                typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
                typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
                typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
                return typedResponse;
            }
        });
    }

    public Flowable<ApprovalEventResponse> approvalEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(APPROVAL_EVENT));
        return approvalEventFlowable(filter);
    }

    @Deprecated
    public static Cash load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new Cash(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static Cash load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new Cash(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static Cash load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new Cash(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static Cash load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new Cash(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<Cash> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Cash.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<Cash> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(Cash.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Cash> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Cash.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<Cash> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(Cash.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class TransferEventResponse extends BaseEventResponse {
        public String from;

        public String to;

        public BigInteger value;
    }

    public static class ApprovalEventResponse extends BaseEventResponse {
        public String owner;

        public String spender;

        public BigInteger value;
    }
}
