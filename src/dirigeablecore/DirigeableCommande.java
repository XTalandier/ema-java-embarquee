/**
 *
 * @author Manu, Yvan
 */
package dirigeablecore;

public class DirigeableCommande {

    public String cmdType = "";
    public double altitude = 0;
    public double longitude = 0;
    public double latitude = 0;

    @Override
    public boolean equals(Object other) 
    {
        DirigeableCommande otherDirigeableCommmande = (DirigeableCommande) other;

        boolean value = false;
        if (other == null) {
            value = false;
        }
        if (other == this) {
            value = true;
        }
        if (!(other instanceof DirigeableCommande)) {
            value = false;
        }

        if (otherDirigeableCommmande.altitude == this.altitude
                && otherDirigeableCommmande.latitude == this.latitude
                && otherDirigeableCommmande.longitude == this.longitude) {
            value = true;
        }

        return value;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (this.cmdType != null ? this.cmdType.hashCode() : 0);
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.altitude) ^ (Double.doubleToLongBits(this.altitude) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
        hash = 37 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        return hash;
    }
}
