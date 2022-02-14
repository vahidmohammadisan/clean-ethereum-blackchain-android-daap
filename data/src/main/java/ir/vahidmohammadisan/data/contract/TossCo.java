package ir.vahidmohammadisan.data.contract;

import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.Arrays;

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
public class TossCo extends Contract {
    public static final String BINARY = "608060405234801561001057600080fd5b50610214806100206000396000f30060806040526004361061004c576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063f8b2cb4f14610051578063fe25e173146100a8575b600080fd5b34801561005d57600080fd5b50610092600480360381019080803573ffffffffffffffffffffffffffffffffffffffff1690602001909291905050506100f9565b6040518082815260200191505060405180910390f35b3480156100b457600080fd5b506100df6004803603810190808035906020019092919080351515906020019092919050505061011a565b604051808215151515815260200191505060405180910390f35b60008173ffffffffffffffffffffffffffffffffffffffff16319050919050565b60008060008443034042604051602001808360001916600019168152602001828152602001925050506040516020818303038152906040526040518082805190602001908083835b6020831015156101875780518252602082019150602081019050602083039250610162565b6001836020036101000a03801982511681845116808217855250505050505090500191505060405180910390206001900491506002828115156101c657fe5b06905060008114156101db57600192506101e0565b600092505b5050929150505600a165627a7a723058208b0e74782e2c84cc2a781b2a7b1d45ccc5e7edbadca238b7cbf6bd9c86a277860029";

    public static final String FUNC_GETBALANCE = "getBalance";

    public static final String FUNC_GUESS = "guess";

    @Deprecated
    protected TossCo(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected TossCo(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected TossCo(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected TossCo(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> getBalance(String owner) {
        final Function function = new Function(FUNC_GETBALANCE,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Address(160, owner)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {
                }));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> guess(BigInteger salt, Boolean guess) {
        final Function function = new Function(FUNC_GUESS,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(salt),
                        new org.web3j.abi.datatypes.Bool(guess)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {
                }));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    @Deprecated
    public static TossCo load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new TossCo(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static TossCo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new TossCo(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static TossCo load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new TossCo(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static TossCo load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new TossCo(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<TossCo> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TossCo.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TossCo> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TossCo.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<TossCo> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(TossCo.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<TossCo> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(TossCo.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
