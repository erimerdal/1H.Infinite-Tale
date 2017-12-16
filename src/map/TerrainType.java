package map;

public class TerrainType {

        private boolean hills;
        private boolean flat;
        private boolean mountain;

        public TerrainType(boolean hills, boolean flat, boolean mountain)
        {
            this.hills = hills;
            this.flat = flat;
            this.mountain = mountain;
        }
        public int getAttackBonus()
        {
            if(hills)
            {
                return -3;
            }
            else if(flat)
            {
                return 3;
            }
            else
            {
                return -4;
            }
        }
        public int getDefenseBonus()
        {
            if(hills)
            {
                return 3;
            }
            else if(flat)
            {
                return -3;
            }
            else
            {
                return 4;
            }
        }

        public int getTerrainType() {
            if(hills)
                return 1;

            if(mountain)
                return 2;

            return 3;
        }
    }

