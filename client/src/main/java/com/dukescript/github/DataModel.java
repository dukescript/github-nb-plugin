package com.dukescript.github;

import java.util.List;
import net.java.html.json.Function;
import net.java.html.json.Model;
import net.java.html.json.OnReceive;
import net.java.html.json.Property;

@Model(className = "ViewModel", targetId = "", properties = {
    @Property(name = "user", type = String.class),
    @Property(name = "repositories", type = RepositoryInfo.class, array = true)
})
final class DataModel {

    @OnReceive(url = "https://api.github.com/users/{name}/repos")
    public static void connect(ViewModel vm, List<RepositoryInfo> repos) {
        vm.getRepositories().clear();
        vm.getRepositories().addAll(repos);
    }
    
    @Function
    public static void loadRepos(ViewModel vm){
        vm.connect(vm.getUser());
    }

    private static ViewModel ui;

    /**
     * Called when the page is ready.
     */
    static void onPageLoad() throws Exception {
        ui = new ViewModel();
        ui.setUser("dukescript");
        ui.applyBindings();
    }

    public static void connect() {

    }

    @Model(className = "RepositoryInfo", properties = {
        @Property(name = "id", type = int.class),
        @Property(name = "name", type = String.class),
        @Property(name = "owner", type = Owner.class),
        @Property(name = "private", type = boolean.class)})
    static class RepositoryModel {
    }

    @Model(className = "Owner", properties = {
        @Property(name = "login", type = String.class)
    })
    static final class OwnerModel {
    }

}
