## Liquid tag 'scastie' used to add an scastie embedding
## in the main text area of the layout
#



module Jekyll::Scastie
  class ScastieBlock < Liquid::Block

	require 'securerandom'

    def initialize(tag_name, block_options, _tokens)
		super

		@local_embed_config = parse_arguments(block_options.strip)
    end

    def render(context)
		code = super

		stamp_config = get_current_stamp_config(context)

		theme = stamp_config.fetch('theme')
		isWorksheetMode = stamp_config.fetch('isWorksheetMode')
		sbtConfig = stamp_config.fetch('sbtConfig')
		targetType = stamp_config.fetch('targetType')
		scalaVersion = stamp_config.fetch('scalaVersion')

		id = "scastie-" + SecureRandom.uuid

		"<div class='#{id}'>#{code}</div>" +
		"<script src='https://scastie.scala-lang.org/embedded.js'></script>" +
		"<script>" +
		"window.addEventListener('load', function() {" +
		"scastie.Embedded('.#{id}', {" +
		"theme: '#{theme}'," +
		"isWorksheetMode: '#{isWorksheetMode}'," +
		"sbtConfig: '#{sbtConfig.gsub("\n",";")}'," +
		"targetType: '#{targetType}'," +
		"scalaVersion: '#{scalaVersion}'" +
		"});" +
		"});" +
		"</script>"
    end

	def parse_arguments(args)
		if(args.empty?)
			{}
		else
			structure = parse_structure(args)

			case structure
			when Hash
				parse_hash_argument(structure)
			else
				raise ArgumentError, "Unsupported scastie tag argument type (#{structure.class}), only Hash is supported: #{args}"
			end
		end
    end

    def parse_structure(args)
      ::SafeYAML.load(args)
    rescue StandardError => e
      raise ArgumentError, "Failed parsing scastie tag argument syntax as YAML: #{args.inspect}. Cause: #{e}"
    end

    def parse_hash_argument(hash)
   	  opts = Hash.new
  	  if(hash.has_key? 'theme')
  		opts = opts.merge({'theme' => hash.fetch('theme')})
  	  end
  	  if(hash.has_key? 'isWorksheetMode')
  		opts = opts.merge({'isWorksheetMode' => hash.fetch('isWorksheetMode')})
  	  end
  	  if(hash.has_key? 'sbtConfig')
  		opts = opts.merge({'sbtConfig' => hash.fetch('sbtConfig')})
  	  end
  	  if(hash.has_key? 'targetType')
  		opts = opts.merge({'targetType' => hash.fetch('targetType')})
  	  end
  	  if(hash.has_key? 'scalaVersion')
  		opts = opts.merge({'scalaVersion' => hash.fetch('scalaVersion')})
  	  end

      opts
    end

	def get_current_stamp_config(context)
		config = context.registers[:site].config['scastie'] || {}

		default_theme = config['theme'] || 'light'
		default_isWorksheetMode = config['default_isWorksheetMode'] || 'true'
		default_sbtConfig = config['sbtConfig'] || ''
		default_targetType = config['targetType'] || 'jvm'
		default_scalaVersion = config['scalaVersion'] || '2.13.3'

		theme = @local_embed_config.fetch('theme', default_theme)
		isWorksheetMode = @local_embed_config.fetch('isWorksheetMode', default_isWorksheetMode)
		sbtConfig = @local_embed_config.fetch('sbtConfig', default_sbtConfig)
		targetType = @local_embed_config.fetch('targetType', default_targetType)
		scalaVersion = @local_embed_config.fetch('scalaVersion', default_scalaVersion)

		{
			'theme'             => theme,
			'isWorksheetMode'   => isWorksheetMode,
			'sbtConfig' 		=> sbtConfig,
			'targetType'        => targetType,
			'scalaVersion'      => scalaVersion
		}
    end
  end
end

Liquid::Template.register_tag('scastie', Jekyll::Scastie::ScastieBlock)
