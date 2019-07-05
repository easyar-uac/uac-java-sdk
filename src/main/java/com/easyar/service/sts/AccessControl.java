package com.easyar.service.sts;

public class AccessControl {
    private String service;
    private Effect effect;
    private String[] resource;
    private Permission[] permission;

    public AccessControl() {
    }

    public AccessControl(String service, Effect effect, String[] resource, Permission[] permission) {
        this.service = service;
        this.effect = effect;
        this.resource = resource;
        this.permission = permission;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public String[] getResource() {
        return resource;
    }

    public void setResource(String[] resource) {
        this.resource = resource;
    }

    public Permission[] getPermission() {
        return permission;
    }

    public void setPermission(Permission[] permission) {
        this.permission = permission;
    }
}
