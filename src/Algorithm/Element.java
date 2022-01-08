package Algorithm;

import java.util.List;

class Element
    {
        int id;
        int fatherId;
        List<Element> children;

        public Element(int id, int fatherId, List<Element> children) {
            this.id = id;
            this.fatherId = fatherId;
            this.children = children;
        }
    }