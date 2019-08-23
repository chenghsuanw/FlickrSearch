package com.example.flickrsearch;

public class PhotoInfo {
    private Info photo;

    public Info getInfo() {
        return photo;
    }

    public PhotoInfo(Info photo) {
        this.photo = photo;
    }

    public class Info {
        private Title title;
        private Description description;
        private Owner owner;

        public Title getTitle() {
            return title;
        }

        public Description getDescription() {
            return description;
        }

        public Owner getOwner() {
            return owner;
        }

        public class Title {
            private String _content;

            public String get_content() {
                return _content;
            }

            public Title(String _content) {
                this._content = _content;
            }
        }
        public class Description {
            private String _content;

            public String get_content() {
                return _content;
            }

            public Description(String _content) {
                this._content = _content;
            }
        }
        public class Owner {
            private String username;
            private String realname;

            public Owner(String username, String realname) {
                this.username = username;
                this.realname = realname;
            }

            public String getUsername() {
                return username;
            }

            public String getRealname() {
                return realname;
            }
        }
    }
}
