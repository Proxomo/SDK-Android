package com.proxomoandroidsdk.definitions;

public class ContinuationToken {
	private String nextPartitionKey;
    private String nextRowKey;

    public ContinuationToken(String nextPartitionKey, String nextRowKey) {
        this.nextPartitionKey = nextPartitionKey;
        this.nextRowKey = nextRowKey;
    }

    public String getNextPartitionKey() {
        return nextPartitionKey;
    }

    public void setNextPartitionKey(String nextPartitionKey) {
        this.nextPartitionKey = nextPartitionKey;
    }

    public String getNextRowKey() {
        return nextRowKey;
    }

    public void setNextRowKey(String nextRowKey) {
        this.nextRowKey = nextRowKey;
    }
}
