## Liquid tag 'epigraph' used to add an epigraph
## in the main text area of the layout
## Usage {% epigraph 'text-body-of-epigraph' 'author-of-epigraph' 'citation-of-epigraph' %}
#
module Jekyll::Tags
	class QuestionTag < Liquid::Block

		def initialize(tag_name, block_options, liquid_options)
		  super
		  # @questionNR = block_options.strip
		  # @questionID = "question-#{block_options.strip}"
		end

		def render(context)
      # site = context.registers[:site]
      # converter = site.find_converter_instance(::Jekyll::Converters::Markdown)

      if(context["questionNR"])
        context["questionNR"] = context["questionNR"] + 1
      else
        context["questionNR"] = 1
      end

      @questionNR = context["questionNR"]
      @questionID = "question-#{context["questionNR"]}"

      context.stack do
				context["questionID"] = @questionID
				context["answer_idx"] = 1
				@content = super.strip
			end

      chapterNR = context.registers[:page]['chapter']
      "<div class=\"question\" id=\"#{@questionID}\"><p><em>Question #{chapterNR}.#{@questionNR}:</em> #{@content}</p></div>"
		end
	end


	class HiddenAnswerTag < Liquid::Block
		def initialize(tag_name, block_options, liquid_options)
		  super
		  @title = block_options.strip
		end

		def render(context)
      idx = context["answer_idx"]
      content = super.strip

      # increment for the next collapsible
      if(idx)
        # question mode
        questionID = context["questionID"]
  			answerID = "#{questionID}-answer-#{idx}"
  			headingID = "#{questionID}-heading-#{idx}"
        context["answer_idx"] = idx + 1
      else
        # solo mode
        if(context["hint_idx"])
          context["hint_idx"] = context["hint_idx"] + 1
        else
          context["hint_idx"] = 1
        end
        answerID = "hint-#{context["hint_idx"]}"
        headingID = "#{answerID}-heading-#{answerID}"
      end

      "<div class=\"answer\" id=\"#{headingID}\"><p><a onclick=\"document.getElementById('#{answerID}').style.display = document.getElementById('#{answerID}').style.display === 'none' ? '' : 'none';\">#{@title}</a></p><div id=\"#{answerID}\" style=\"display: none;\"><p>#{content}</p></div></div>"

		end
	end

  class AnswerTag < Liquid::Block
		def initialize(tag_name, block_options, liquid_options)
		  super
		  @title = block_options.strip
		end

		def render(context)
			questionID = context["questionID"]
			idx = context["answer_idx"]
			answerID = "#{questionID}-answer-#{idx}"
			headingID = "#{questionID}-heading-#{idx}"

			# increment for the next collapsible
			context["answer_idx"] = idx + 1
      content = super.strip
			"<div class='answer' id='#{headingID}'><p><em>#{@title}</em>: #{content}</p></div>"

		end
	end

  class StopThinkTag < Liquid::Block
    def initialize(tag_name, block_options, liquid_options)
      super
    end

    def render(context)
      content = super
      "<p class=\"stopandthink\"><em>Stop and think: </em>#{content}</p>"
    end
  end
end

Liquid::Template.register_tag('question', Jekyll::Tags::QuestionTag)
Liquid::Template.register_tag('hidden', Jekyll::Tags::HiddenAnswerTag)
Liquid::Template.register_tag('answer', Jekyll::Tags::AnswerTag)
Liquid::Template.register_tag('stopandthink', Jekyll::Tags::StopThinkTag)
