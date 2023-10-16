public class ShopItem {
    private int saveGameLimited;

    private int getSaveGameLimited_Still;

    private String rankUser;

    public ShopItem() {
    }

    public ShopItem(int saveGameLimited, int getSaveGameLimited_Still, String rankUser) {
        this.saveGameLimited = saveGameLimited;
        this.getSaveGameLimited_Still = getSaveGameLimited_Still;
        this.rankUser = rankUser;
    }

    public int getSaveGameLimited() {
        return saveGameLimited;
    }

    public void setSaveGameLimited(int saveGameLimited) {
        this.saveGameLimited = saveGameLimited;
    }

    public int getGetSaveGameLimited_Still() {
        return getSaveGameLimited_Still;
    }

    public void setGetSaveGameLimited_Still(int getSaveGameLimited_Still) {
        this.getSaveGameLimited_Still = getSaveGameLimited_Still;
    }

    public String getRankUser() {
        return rankUser;
    }

    public void setRankUser(String rankUser) {
        this.rankUser = rankUser;
    }
}
