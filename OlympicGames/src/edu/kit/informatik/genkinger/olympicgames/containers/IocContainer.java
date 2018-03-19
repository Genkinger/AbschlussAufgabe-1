package edu.kit.informatik.genkinger.olympicgames.containers;

import edu.kit.informatik.Utils;
import edu.kit.informatik.genkinger.olympicgames.Clearable;
import edu.kit.informatik.genkinger.olympicgames.IocCode;
import edu.kit.informatik.genkinger.olympicgames.IocCodeComparator;

import java.util.ArrayList;

public class IocContainer extends Container implements Clearable {
    private ArrayList<IocCode> iocCodes = new ArrayList<>();

    public boolean addIocCode(int id, String code, String countryName, int year) {
        if (findIocCodeByCode(code) != null) {
            setErrorString("IocCode with that code already exists");
            return false;
        }
        if (findIocCodeByCountry(countryName) != null) {
            setErrorString("IocCode for '" + countryName + "' already exists");
            return false;
        }
        if (findIocCodeById(id) != null) {
            setErrorString("IocCode with that id already exists");
            return false;
        }

        if (!Utils.inRange(id, 0, 999)) {
            setErrorString("invalid id");
            return false;
        }

        if (!validateYear(year)) {
            setErrorString("invalid year");
            return false;
        }

        iocCodes.add(new IocCode(id, code, countryName, year));

        iocCodes.sort(new IocCodeComparator());
        return true;
    }

    public IocCode findIocCodeByCode(String code) {
        for (IocCode iocCode : iocCodes) {
            if (iocCode.getCode().equals(code)) {
                return iocCode;
            }
        }
        return null;
    }

    public IocCode findIocCodeById(int id) {
        for (IocCode iocCode : iocCodes) {
            if (iocCode.getId() == id) {
                return iocCode;
            }
        }
        return null;
    }

    public IocCode findIocCodeByCountry(String country) {
        for (IocCode iocCode : iocCodes) {
            if (iocCode.getCountryName().equals(country)) {
                return iocCode;
            }
        }
        return null;
    }

    //TODO: do real checking
    private boolean validateYear(int year) {
        if (Utils.inRange(year, 0, 9999)) {
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        iocCodes.clear();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (IocCode iocCode : iocCodes) {
            builder.append(iocCode);
            builder.append("\n");
        }
        return builder.toString().trim();
    }
}
