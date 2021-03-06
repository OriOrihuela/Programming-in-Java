package org.lasencinas.tokenContract;

import org.lasencinas.address.Address;


import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class TokenContract {

    /* ---- Propiedades ---- */
    private String name = null;
    private String symbol = null;
    private double totalSupply = 0;
    private PublicKey ownerPK = null;
    private Address owner = null;
    private String tokenSymbol = "RNiLL";
    private double tokenPrice = 5;

    private Map<PublicKey, Double> balances = new HashMap<>();


    /* ---- Constructor ---- */
    public TokenContract(Address address) {
        this.owner = address;
        this.ownerPK = address.getPK();
        this.symbol = address.getSymbol();
        this.totalSupply = address.getBalance();
    }


    /* ---- Getters ---- */
    public PublicKey getOwnerPK() {
        return ownerPK;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String symbol() {
        return tokenSymbol;
    }

    public Map<PublicKey, Double> getBalances() {
        return balances;
    }

    public double totalSupply() {
        return totalSupply;
    }

    public double getTokenPrice() {
        return tokenPrice;
    }

    /* ---- Setters ---- */
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTotalSupply(double totalSupply) {
        this.totalSupply = totalSupply;
    }

    public void setTokenPrice(double tokenPrice) {
        this.tokenPrice = tokenPrice;
    }

    /* ----- Métodos principales ---- */
    @Override
    public String toString() {
        return "\n" + "name = " + getName() + "\n" +
                "symbol = " + getSymbol() + "\n" +
                "totalSupply " + totalSupply() + "\n" +
                "owner PK " + getOwnerPK().hashCode();
    }

    public void addOwner(PublicKey PK, Double units) {
        getBalances().putIfAbsent(PK, units);
    }

    public int numOwners() {
        return getBalances().size();
    }

    public double balanceOf(PublicKey owner) {
        if (getBalances().containsKey(owner)) {
            return getBalances().get(owner);
        } else {
            return 0;
        }
    }

    private void require(Boolean holds) throws Exception {
        if (!holds) {
            throw new Exception();
        } else {
            // Pass
        }
    }

    public void transfer(PublicKey recipient, double units) {
        try {
            require(balanceOf(getOwnerPK()) > units);
            getBalances().put(getOwnerPK(), balanceOf(getOwnerPK()) - units);
            getBalances().put(recipient, balanceOf(recipient) + units);
        } catch (Exception e) {
            ;
        }
    }

    public void transfer(PublicKey sender, PublicKey recipient, double units) {
        try {
            require(balanceOf(sender) > units);
            getBalances().put(sender, balanceOf(sender) - units);
            getBalances().put(recipient, balanceOf(recipient) + units);
        } catch (Exception e) {
            ;
        }
    }

    public void owners() {
        for (Map.Entry<PublicKey, Double> PK : getBalances().entrySet()) {
            if (PK.getKey().hashCode() != getOwnerPK().hashCode()) {
                System.out.println("Owner: " + PK.getKey().hashCode() + " " + PK.getValue() + " " + getSymbol());
            }

        }
    }

    public double totalTokensSold() {
        double totalTokensSold = 0d;
        for (PublicKey PK : getBalances().keySet()) {
            if (!PK.equals(getOwnerPK())) {
                totalTokensSold += getBalances().get(PK);
            }
        }
        return totalTokensSold;
    }

    public void payable(PublicKey recipient, Double enziniums) {
        Double units = Math.floor(enziniums / tokenPrice);
        try {
            require(enziniums >= getTokenPrice());
            transfer(recipient, enziniums);
            this.owner.transferEZI(enziniums);
        } catch (Exception e) {
            // Pass
        }
    }
}
