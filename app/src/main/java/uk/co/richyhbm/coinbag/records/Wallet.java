package uk.co.richyhbm.coinbag.records;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import uk.co.richyhbm.coinbag.balances.Balance;
import uk.co.richyhbm.coinbag.enums.CryptoCurrencies;

//Wallet class, uses SugarORM to store in sqlite db
public class Wallet extends SugarRecord {
    //Store the address and type of crypto currency of the wallet
    @Unique
    String address;
    CryptoCurrencies cryptoType;

    public Wallet() {

    }

    public Wallet(String address, CryptoCurrencies type){
        this.address = address;
        this.cryptoType = type;
    }

    public CryptoCurrencies getType() {
        return cryptoType;
    }

    public String getAddress() {
        return address;
    }

    //Returns the balance of a wallet using the right API for that crypto type
    //TODO: Create a class based off of AsyncTask that takes in a Wallet and returns balance or value
    public String getBalance() {
        if(Balance.balanceFetchers.containsKey(cryptoType)){
            return Balance.balanceFetchers.get(cryptoType).getBalanceForAddress(address);
        }
        return "";
    }

    //Returns the current fiat value of the wallet
    public String getValue() {
        return "";
    }
}