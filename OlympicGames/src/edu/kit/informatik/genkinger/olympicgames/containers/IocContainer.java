package edu.kit.informatik.genkinger.olympicgames.containers;

import edu.kit.informatik.genkinger.olympicgames.Clearable;
import edu.kit.informatik.genkinger.olympicgames.IocCode;
import edu.kit.informatik.genkinger.olympicgames.comparators.IocCodeComparator;

import java.util.ArrayList;

/**
 *
 */
public class IocContainer extends Container implements Clearable {
    private ArrayList<IocCode> iocCodes = new ArrayList<>();

    /**
     *
     * @param id .
     * @param code .
     * @param countryName .
     * @param year .
     * @return .
     */
    public boolean addIocCode(String id, String code, String countryName, String year) {

        if (!id.matches("[0-9]{3}") || id.equals("000")) {
            setErrorString("invalid id");
            return false;
        }

        if (!year.matches("[0-9]{4}")) {
            setErrorString("invalid year");
            return false;
        }

        if (!code.matches("[a-z]{3}")) {
            setErrorString("invalid code");
            return false;
        }

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

        iocCodes.add(new IocCode(id, code, countryName, year));
        iocCodes.sort(new IocCodeComparator());

        return true;
    }

    /**
     *
     * @param country .
     * @return .
     */
    IocCode findIocCodeByCountry(String country) {
        for (IocCode iocCode : iocCodes) {
            if (iocCode.getCountryName().equals(country)) {
                return iocCode;
            }
        }
        return null;
    }

    private IocCode findIocCodeByCode(String code) {
        for (IocCode iocCode : iocCodes) {
            if (iocCode.getCode().equals(code)) {
                return iocCode;
            }
        }
        return null;
    }

    private IocCode findIocCodeById(String id) {
        for (IocCode iocCode : iocCodes) {
            if (iocCode.getId().equals(id)) {
                return iocCode;
            }
        }
        return null;
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
