require "spec_helper"

describe Jekyll::ScalaFiddle::ScalaFiddleTag do
  let(:site) { make_site }
  before { site.process }

  context "full page rendering" do
    let(:content) { File.read(dest_dir("page.html")) }

    it "creates tags" do
      expect(content).to match(%r!(data-scalafiddle)!)
      expect(content).to match(%r!(data-template)!)
      expect(content).to match(%r!(data-prefix="import cats.data._")!)
      expect(content).to match(%r!(data-scalaversion="2.11")!)
    end

    it "includes external js only once" do
      expect(content).to match(%r!(integration\.js)!)
      expect(content).to match(%r!(window\.scalaFiddleTemplates)!)
      expect(content.scan(%r!integration\.js!).length).to eq(1)
      puts content
    end
  end

  context "options rendering" do
    let(:page) { make_page }
    let(:site) { make_site }
    let(:context) { make_context(:page => page, :site => site) }
    let(:tag) { "scalafiddle" }

    context "render all attributes" do
      let(:options) do
        "template='testing' minheight='600' theme='dark' layout='v50'"
      end
      let(:output) do
        Liquid::Template.parse("{% #{tag} #{options} %}Testing{% end#{tag} %}").render!(context, {})
      end

      it "renders attributes" do
        expected = %r!div data-scalafiddle data-template='testing' data-scalaversion='2.11' data-minheight='600' data-layout='v50' data-theme='dark'!
        expect(output).to match(expected)
      end
    end
  end
end
