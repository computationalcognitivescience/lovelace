lib = File.expand_path("../lib", __FILE__)
$LOAD_PATH.unshift(lib) unless $LOAD_PATH.include?(lib)
require "jekyll-scalafiddle/version"

Gem::Specification.new do |spec|
  spec.name          = "jekyll-scalafiddle"
  spec.summary       = "Jekyll ScalaFiddle integration"
  spec.description   = "Easily embed ScalaFiddle in Jekyll pages"
  spec.version       = Jekyll::ScalaFiddle::VERSION
  spec.authors       = ["Otto Chrons"]
  spec.email         = ["otto@chrons.me"]

  spec.homepage      = "https://github.com/scalafiddle/scalafiddle-core/tree/master/integrations/jekyll"
  spec.licenses      = ["Apache-2.0"]
  spec.files         = `git ls-files -z`.split("\x0").reject { |f| f.match(%r!^(test|spec|features)/!) }
  spec.require_paths = ["lib"]

  spec.add_dependency "jekyll", "~> 4.0"

  spec.add_development_dependency "rake", "~> 11.0"
  spec.add_development_dependency "rspec", "~> 3.5"
  spec.add_development_dependency "rubocop", "0.49.1"
end
