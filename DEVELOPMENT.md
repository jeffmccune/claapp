## Running Locally

Make sure you have Clojure installed.  Also, install the [Heroku
Toolbelt](https://toolbelt.heroku.com/).

```sh
$ git clone https://github.com/heroku/clojure-getting-started.git
$ cd clojure-getting-started
$ lein repl
user=> (require 'clojure-getting-started.web)
user=>(def server (clojure-getting-started.web/-main))
```

Your app should now be running on [localhost:5000](http://localhost:5000/).

### Database Setup

Here's how I got things going on Mac OS X.  All ran as my normal user account,
not as root.

    brew install postgres

Initialize the databases:

    initdb /usr/local/var/postgres -E utf8

Configure to run at login:

    ln -sfv /usr/local/opt/postgresql/*.plist ~/Library/LaunchAgents

Start the database now:

    launchctl load ~/Library/LaunchAgents/homebrew.mxcl.postgresql.plist

Create the DB for the application:

    createdb mydb

Create the role for the application:

    psql claapp --command="CREATE USER claapp WITH PASSWORD '${DATABASE_PASSWORD:-claapp}';"

With this configuration, the ragtime database connection string specified in
project.clj should connect to the local postgres database.

## Deploying to Heroku

```sh
$ heroku create
$ heroku addons:add heroku-postgresql:hobby-dev
$ git push heroku master
$ heroku open
```

### Database Migration

To be added.  We need to modify the code to read the database connection string
from the enviornment.

## Troubleshooting

If you're unable to run `heroku run lein ragtime migrate`, try `heroku run
'lein do clean; lein ragtime migrate;'` as a workaround.
