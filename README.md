# Jekyll academic book templates
## Installation and setup
1. Clone the repository (you probably already have)
2. Install a [Ruby environment](https://jekyllrb.com/docs/installation/)
3. Install jekyll and bundler gems: ```gem install jekyll bundler```
4. Navigate to the cloned repository
5. Install jekyll site plugins: ```bundle install```
6. Serve the site: ```bundle exec jekyll serve```
7. Browse to [http://localhost:4000](http://localhost:4000)

## Publishing the site
1. Make sure you have a target repository and GitHub account
2. Enter your GitHub credentials and info in ```deploy.env```
3. When publishing for the first time, give execution rights to the script:
```chmod +x ./deploy.sh```
4. Run the deployment script: ```./deploy.sh```, it may ask for your GitHub password.



## Modifications

Add your bibtex files to ```_bibliography```, by default only ```references.bib```
is used.

Update the copyright message in ```_includes/copyright.html```.

If using Scalafiddle and you want to add more templates, add them to
```_scalafiddle```. The default template file ```_scalafiddle/Template.scala```
can be used as a base and contains basic plotting functionality.
